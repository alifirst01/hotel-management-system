package com.roux.controller;

import com.roux.model.Room;
import com.roux.model.RoomImages;
import com.roux.service.RoomService;
import com.roux.service.roomImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private roomImageServices RIS;

    public void accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/accessDenied.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int RoomCount = 1;
    public static int BookingCount = 1;

    public Date startDate;
    public Date endDate;

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public void AddRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest RI) {
        HttpSession session = request.getSession(true);
        Object roll = session.getAttribute("type");
        if(roll == null || ((int)roll) != 1) {
            accessDeniedPage(request, response);
            return;
        }

        String type = request.getParameter("type");

        float price = Float.parseFloat(request.getParameter("price"));
        Room R = new Room();
        R.setId(RoomCount);
        R.setType(type);
        R.setPrice(price);
        try {
            saveImage(R, RI.getFiles("pictures"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int ans = -1;
        ans = roomService.addRoom(RoomCount, type, price);
        RoomCount++;

        String panel = addRoomPanels(roomService.getRooms(), RIS, 1);

        request.setAttribute("panel", panel);
        try {
            request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public void SearchRoom(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Object roll = session.getAttribute("type");
        if(roll == null || ((int)roll) != 3) {
            accessDeniedPage(request, response);
            return;
        }

        String DateIn = request.getParameter("cinday");
        String DateOut = request.getParameter("coutday");
        String type = request.getParameter("type");

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        List<Room> AvailableRooms = null;

        try {
            startDate = df.parse(DateIn);
            endDate = df.parse(DateOut);
            AvailableRooms = roomService.searchRooms(startDate, endDate, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(startDate.compareTo(endDate) > 0){
            request.setAttribute("panel", "Invalid Date Input");
            try {
                request.getRequestDispatcher("/views/Room.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String roompanel = addRoomPanels(AvailableRooms, RIS, 2);
        request.setAttribute("roompanel", roompanel);
        try {
            request.getRequestDispatcher("/views/AvailableRooms.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/reserveRoom", method = RequestMethod.POST)
    public String reserve(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object roll = session.getAttribute("type");
        if(roll == null || ((int)roll) != 3) {
            accessDeniedPage(request, response);
            return null;
        }

        int UID = (int) session.getAttribute("userID");
        int RID = Integer.parseInt(request.getParameter("rid"));
        float price = Float.parseFloat(request.getParameter("price"));

        int ans = roomService.reserve(BookingCount, UID, RID, startDate, endDate, price);
        BookingCount++;

        return "reservations";
    }

    @RequestMapping(value = "/addroomservice", method = RequestMethod.POST)
    public void addService(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession(true);
        Object roll = session.getAttribute("type");
        if(roll == null || ((int)roll) != 1) {
            accessDeniedPage(request, response);
            return;
        }

        String b = null;
        String i = null;
        String c = null;
        int id = Integer.parseInt(request.getParameter("id"));
        if(request.getParameterValues("bed") != null)
            b= new String("Bed");
        if(request.getParameterValues("internet") != null)
            i= new String("Internet");
        if(request.getParameterValues("carpet") != null)
            c= new String("Carpet");
        roomService.addServices(id, b, i,c);

        String panel = addRoomPanels(roomService.getRooms(), RIS,1);
        request.setAttribute("panel", panel);
        try {
            request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String addRoomPanels(List<Room> Rooms, roomImageServices RIS, int flag){
        String path = "/resources/images/newRooms/";
        String panel = new String();
        String addServiceForm = new String();
        String reserveRoomForm = new String();
        String form;
        int id = 1;
        if(Rooms.size() == 0)
            panel = "<div style='color:white;'>No Rooms Available on specified time</div>";
        for(int i = 0; i < Rooms.size(); i++){
            Room R = Rooms.get(i);
            ArrayList<String> images = RIS.getImages(R.getId());

            addServiceForm = "        <br><label>Add Services: </label>\n" +
                    "            <form action=\"/addroomservice\" method=\"post\">\n" +
                    new String((R.getS1() == null)? "\t\t\t\t<label><input name = \"bed\" type=\"checkbox\" value=\"bed\">Bed</label><br>":"        \t<label><input name = \"bed\" type=\"checkbox\" value=\"bed\" checked>Bed</label><br>") +
                    new String((R.getS2() == null)? "\t\t\t\t<label><input name = \"internet\" type=\"checkbox\" value=\"internet\">Internet</label><br>":"        \t<label><input name = \"internet\" type=\"checkbox\" value=\"internet\" checked>Internet</label><br>") +
                    new String((R.getS3() == null)? "  \t\t\t\t<label><input name = \"carpet\" type=\"checkbox\" value=\"carpet\">Carpet</label><br>\n":"  \t\t\t\t<label><input name = \"carpet\" type=\"checkbox\" value=\"carpet\" checked>Carpet</label><br>\n") +
                    "              <input hidden name=\"id\" value=\"" + Integer.toString(R.getId())+ "\">" +
                    "              <button type=\"submit\" class=\"btn btn-primary\"style=\"float:right;\">Update Services</button>\n" +
                    "            </form>\n";

            reserveRoomForm = "<form action=\"/reserveRoom\" method=\"post\" style=\"float: right\">\n" +
                    "              <input hidden name=\"rid\" value=\"" + Integer.toString(R.getId())+ "\">" +
                    "              <input hidden name=\"price\" value=\"" + Double.toString(R.getPrice())+ "\">" +
                    "\t\t\t\t<button type=\"submit\" class=\"btn btn-primary\"style=\"float:right;\">Reserve Room</button>\n" +
                            "\t\t\t</form>";

            if(flag == 1)
                form = addServiceForm;
            else
                form = reserveRoomForm;

            String imgs = "<div class=\"container\">\n" + "<div class=\"row\">\n";
            for(int j = 0; j < images.size(); j++){
                String imgpath = path + images.get(j);
                imgs = imgs + "<div class=\"col col-lg-2\"><a href=\"#\"><img class=\"thumbnail img-responsive\" src = \"<c:url value= '" + imgpath + "' />\"></a></div>\n";
            }
            imgs = imgs +  "</div>\n" + "</div>";

            panel = panel + "<div class=\"panel-group\" id=\"accordion\">\n" +
                            "  <div class=\"panel panel-default template\">\n" +
                            "    <div class=\"panel-heading\">\n" +
                            "      <h4 class=\"panel-title\">\n" +
                            "        <a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+Integer.toString(id) +"\">\n" +
                            "          <ul style=\"list-style-type: none;\">\n" +
                            "          \t<li style=\"display: inline;\">Room "+Integer.toString(R.getId())+"</li>\n" +
                            "          \t<li style=\"float: right;display: inline;\">Price: $" + String.valueOf(R.getPrice()) + "</li>\n" +
                            "          </ul>\n" +
                            "        </a>\n" +
                            "      </h4>\n" +
                            "    </div>\n" +
                            "    <div id=\"collapse"+Integer.toString(id)+"\" class=\"panel-collapse collapse\">\n" +
                            "      <div class=\"panel-body\">\n" +
                            "      <label>Type: </label>\n" + R.getType()+
                            "      <br><br><label>Services: </label>\n" +
                            "        <ul style=\"display: block;\">\n" +
                            "        \t<li style=\"display: inline;float: right\">\n" +
                            "    \t\t\t&nbsp&nbsp<label >Status: </label>" + " " + R.getStatus() + "\n" +
                            "    \t\t</li>\n" +
                                  new String((R.getS1() == null)? "":"        \t<li>Bed</li>\n") +
                                  new String((R.getS2() == null)? "":"        \t<li>Internet</li>\n") +
                                  new String((R.getS3() == null)? "":"        \t<li>Carpet</li>\n") +
                            "        </ul>\n" +
                            "<br />\n" +
                            imgs +
                            form +
                            "  \t\t\n" +
                            "      </div>\n" +
                            "    </div>\n" +
                            "  </div>" +
                            "</div>";
            id++;
        }
        return panel;
    }

    private void saveImage(Room room, List<MultipartFile> images) throws IOException {
        final String path = "C:\\Users\\Ali Asgher\\Desktop\\ROUX Hotel\\src\\main\\webapp\\resources\\images\\newRooms\\";
        for (int i = 0; i < images.size(); i++) {
            RoomImages roomImage = new RoomImages();
            String roomid = Integer.toString(room.getId());
            String name = "id_" + roomid + "_" +  + i + ".jpg";
            roomImage.setName(name);
            File file = new File(path + name);

            int fileNo = i;
            while (file.exists() && !file.isDirectory()) {
                fileNo++;
                String newName = name.replaceAll(".jpg", "(" + fileNo + ").jpg");
                file = new File(newName);
                roomImage.setName(newName);
            }

            if (file.createNewFile()) {
                FileOutputStream save = new FileOutputStream(path + name);
                save.write(images.get(i).getBytes());
                save.close();
            }
            roomImage.setRoom(room.getId());
            RIS.save(roomImage);
        }
    }

}
