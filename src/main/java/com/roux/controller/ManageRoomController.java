package com.roux.controller;

import com.roux.model.Bookings;
import com.roux.model.Room;
import com.roux.service.RoomService;
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
public class ManageRoomController {

    @Autowired
    private RoomService roomService;

    public void accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/accessDenied.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getpendingrooms", method = RequestMethod.GET)
    public void manageRooms(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type == null || ((int)type) != 2) {
            accessDeniedPage(request, response);
            return;
        }

        List<Room> Rooms = roomService.getPendingRooms();
        String RPanel = roomPanel(Rooms);
        request.setAttribute("RPanel", RPanel);
        try {
            request.getRequestDispatcher("/views/manager.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/changePrice", method = RequestMethod.POST)
    public void changePrive(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type == null || ((int)type) != 2) {
            accessDeniedPage(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        float price = Float.parseFloat(request.getParameter("price"));
        int ans = roomService.changePrice(id, price);
        manageRooms(request, response);
    }

    @RequestMapping(value = "/approveRoom", method = RequestMethod.POST)
    public void approveRoom(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type == null || ((int)type) != 2) {
            accessDeniedPage(request, response);
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        int ans = roomService.approveRoom(id);
        manageRooms(request, response);
    }

    public String roomPanel(List<Room> Rooms) {
        String panel = new String();
        int index = 1;
        for (int i = 0; i < Rooms.size(); i++) {
            Room R = Rooms.get(i);
            panel = panel + "<div class=\"panel panel-default template\">\n" +
                    "    <div class=\"panel-heading\">\n" +
                    "      <h4 class=\"panel-title\">\n" +
                    "        <a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse" + Integer.toString(index) + "\">\n" +
                    "          <ul style=\"list-style-type: none;\">\n" +
                    "          \t<li style=\"display: inline;\">Room " + Integer.toString(R.getId()) + "</li>\n" +
                    "          \t<li style=\"float: right;display: inline;\">Price: $" + Double.toString(R.getPrice()) + "</li>\n" +
                    "          </ul>\n" +
                    "        </a>\n" +
                    "      </h4>\n" +
                    "    </div>\n" +
                    "    <div id=\"collapse" + Integer.toString(index) + "\" class=\"panel-collapse collapse\">\n" +
                    "      <div class=\"panel-body\">\n" +
                    "      \t\t<label>Status: &emsp;</label>Pending\n" +
                    "\t\t\t<form action=\"/approveRoom\" method=\"post\">\n" +
                    "              <input hidden name=\"id\" value=\"" + Integer.toString(R.getId()) + "\">" +
                    "      \t\t<button type=\"submit\" class=\"btn btn-success\"style=\"float:right;\">Approve Room</button>\n" +
                    "      \t\t<br>\n" +
                    "\t\t\t</form>\n" +
                    "      \t\t<hr/>\n" +
                    "\t\t\t<form action=\"/changePrice\" method=\"post\">\n" +
                    "      \t\t\t<div class =\"form-group\">\n" +
                    " \t\t\t\t\t<label for=\"text\" style=\"float:left;margin-right:5px;\">Change Price: $</label>       \n" +
                    " \t\t\t\t\t<div class=\"input-group\">   \n" +
                    " \t\t\t\t\t\t<input type=\"tex\" name=\"price\" placeholder=\"Enter New Price\">                                          \n" +
                    " \t\t\t\t\t</div>   \n" +
                    " \t\t\t\t</div>\n" +
                    "         \t\t<br>\n" +
                    "              <input hidden name=\"id\" value=\"" + Integer.toString(R.getId()) + "\">" +
                    "\t\t\t\t<button type=\"submit\" class=\"btn btn-danger\"style=\"float:right;\">Update</button>\t\n" +
                    "\t\t\t</form>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </div>";
            index++;
        }
        return panel;
    }
}
