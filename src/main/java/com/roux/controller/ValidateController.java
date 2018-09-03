package com.roux.controller;

import com.roux.model.Room;
import com.roux.model.User;
import com.roux.model.Userroles;
import com.roux.service.MailService;
import com.roux.service.RoomService;
import com.roux.service.UserService;
import com.roux.service.roomImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ValidateController {
    @Autowired
    UserService userService;

    @Autowired
    private RoomService roomService;

    public int UserCount = 1;

    @Autowired
    MessageSource messageSource;

    @Autowired
    MailService mailService;

    @Autowired
    private roomImageServices RIS;

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public String AddUser(@Valid @ModelAttribute("user") User U, BindingResult result,
                        HttpServletRequest request, HttpServletResponse response,
                        ModelMap model) {

        if(result.hasErrors()){
            request.setAttribute("message", "Error: Username or Email already exists");
            return "signup";
        }
        try {
            UserCount = userService.getUserCount() + 1;
            U.setId(UserCount);
            U.setToken(UUID.randomUUID().toString());
            userService.save(U);
            userService.addUserRole(U.getId(), 3);
        }
        catch (Exception E){
            request.setAttribute("message", "Error Registering User: Username or Email must be unique");
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return null;
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mailService.sendConfirmationEmail(U);
        request.setAttribute("message", "Confirmation Email sent. Please Verify to login");
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return null;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
            User user = new User();
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            return "signup";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public void SignIn(HttpServletRequest request, HttpServletResponse response) {
        SignIp(request, response);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String SignIp(HttpServletRequest request, HttpServletResponse response) {
        String Username = request.getParameter("username");
        String Password = request.getParameter("password");
        Userroles userrole = null;
        try {
            userrole = userService.validateUser(Username, Password);
        }
        catch (Exception E){
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(userrole.getRid() == -1) {
            request.setAttribute("message", "Username or Password is Incorrect");
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User U = userService.getUser(userrole.getUid());

        if(U.getToken().compareTo("Verified") != 0){
            request.setAttribute("message", "Username not verified. Please verify through email to login");
            try {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("userID", userrole.getUid());
        session.setAttribute("type", userrole.getRid());
        session.setAttribute("name", U.getFirstName() + " " + U.getLastName());
        session.setAttribute("fname", U.getFirstName());
        session.setAttribute("lname", U.getLastName());
        session.setAttribute("username", U.getUsername());
        session.setAttribute("email", U.getEmail());
        session.setAttribute("contact", U.getContact());
        session.setAttribute("country", U.getCountry());
        session.setAttribute("zipcode", U.getZipCode());

        request.setAttribute("name", session.getAttribute("name"));

        if(userrole.getRid() == 1) {
            List<Room> Rooms = roomService.getRooms();
            String panel = RoomController.addRoomPanels(Rooms, RIS, 1);
            RoomController.RoomCount = Rooms.size() + 1;
            request.setAttribute("panel", panel);
            try {
                request.getRequestDispatcher("/views/admin.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(userrole.getRid() == 2) {
            try {
                request.getRequestDispatcher("/views/manager.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                request.getRequestDispatcher("/views/Room.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
