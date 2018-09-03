package com.roux.controller;

import com.roux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/", "/home", "/login"})
    public void home(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/errors/Access_Denied", method = RequestMethod.GET)
    public void accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/accessDenied.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/errors/404"})
    public String pageNotFound() {
        return "404";
    }

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public String RoomPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null && ((int)type) == 3) {
            return "Room";
        }
        else{
            if(type == null) {
                request.setAttribute("message", "Sign In to Book A Room");
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            accessDeniedPage(request, response);
            return null;
        }
    }

    @RequestMapping(value = "/administrator", method = RequestMethod.GET)
    public String AdminPage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null && ((int)type) == 1) {
            return "admin";
        }
        else{
            accessDeniedPage(request, response);
            return null;
        }
    }

    @RequestMapping(value = "/manageroom", method = RequestMethod.GET)
    public String ManagerPage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null && ((int)type) == 2) {
            return "manager";
        }
        else{
            accessDeniedPage(request, response);
            return null;
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String UserPage(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null && ((int)type) == 3) {
            return "Room";
        }
        else{
            accessDeniedPage(request, response);
            return null;
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public void ProfilePage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null) {
            try {
                request.getRequestDispatcher("/views/viewProfile.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            accessDeniedPage(request, response);
        }


    }

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public String ReservationPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Object type = session.getAttribute("type");
        if(type != null && ((int)type) == 3) {
            return "reservations";
        }
        else{
            accessDeniedPage(request, response);
            return null;
        }
    }

    @RequestMapping(value = "/signout")
    public void signout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("userID");
        session.removeAttribute("type");
        session.removeAttribute("name");
        session.removeAttribute("fname");
        session.removeAttribute("lname");
        session.removeAttribute("username");
        session.removeAttribute("email");
        session.removeAttribute("contact");
        session.removeAttribute("country");
        session.removeAttribute("zipcode");
        home(request, response);
    }

    @RequestMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/user/profileid-{username}/{token}/confirm")
    public void verify(@PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response, @PathVariable("token") String token){
        userService.verifyUser(username);
        request.setAttribute("message", "Username Verified");
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}