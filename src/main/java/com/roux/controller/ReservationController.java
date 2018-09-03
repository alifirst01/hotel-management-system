package com.roux.controller;

import com.roux.model.Bookings;
import com.roux.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    public void accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/accessDenied.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getAllReservations", method = RequestMethod.GET)
    public void getAllMyReservations(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type == null || ((int)type) != 3) {
            accessDeniedPage(request, response);
            return;
        }

        int UID = (int) session.getAttribute("userID");
        List<Bookings> Reservations = null;
        try {
            Reservations = reservationService.getAllRes(UID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String respanel = resPanels(Reservations);
        request.setAttribute("panel", respanel);
        try {
            request.getRequestDispatcher("/views/reservations.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/cancelRes", method = RequestMethod.GET)
    public void cancelReservations(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type == null || ((int)type) != 3) {
            accessDeniedPage(request, response);
            return;
        }
        reservationService.cancelRes(Integer.parseInt(request.getParameter("id")));
        getAllMyReservations(request,response);

    }

    public String resPanels(List<Bookings> Res){
        String panel = new String();
        int index = 1;
        for(int i = 0; i < Res.size(); i++){
            Bookings B = Res.get(i);
            panel = panel + "<div class=\"panel panel-default template\">\n" +
                    "    <div class=\"panel-heading\">\n" +
                    "      <h4 class=\"panel-title\">\n" +
                    "        <a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+Integer.toString(index) +"\">\n" +
                    "          <ul style=\"list-style-type: none;\">\n" +
                    "          \t<li style=\"display: inline;\">Reservation: " + Integer.toString(B.getId()) + "</li>\n" +
                    "          \t<li style=\"float: right;display: inline;\">Price: $" + Double.toString(B.getPrice()) + "</li>\n" +
                    "          </ul>\n" +
                    "        </a>\n" +
                    "      </h4>\n" +
                    "    </div>\n" +
                    "    <div id=\"collapse"+Integer.toString(index)+"\" class=\"panel-collapse collapse\">\n" +
                    "      <div class=\"panel-body\">\n" +
                    "      \t<ul style=\"list-style-type: none;\">\n" +
                    "      \t\t<li><label>Room: </label>1</li>\n" +
                    "      \t\t<li><label>CheckIn Date: &emsp;</label>" + " " + B.getCheckInDate().toString() + "</li>\n" +
                    "      \t\t<li><label>CheckOut Date: &emsp;</label>" + " " + B.getCheckOutDate().toString() + "</li>\n" +
                    "      \t\t<li style=\"float:right\">\n" +
                    "      \t\t\t<form action=\"/cancelRes\" method=\"get\">\n" +
                    "              <input hidden name=\"id\" value=\"" + Integer.toString(B.getId())+ "\">" +
                    "\t\t\t\t\t<button type=\"submit\" class=\"btn btn-danger\"style=\"float:right;\">Delete Reservation</button>\n" +
                    "\t\t\t\t</form>\n" +
                    "      \t\t</li>\n" +
                    "      \t</ul>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </div>";
            index++;
        }
        return panel;
    }
}
