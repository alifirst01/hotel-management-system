package com.roux.controller;

import com.roux.model.User;
import com.roux.service.RoomService;
import com.roux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/availability")
    public @ResponseBody String checkUsernameAvailability(@RequestParam("username") String username) {
        return userService.getUserbyUsername(username);
    }

    @RequestMapping(value = "/email/availability")
    public @ResponseBody String checkEmailAvailability(@RequestParam("email") String email) {
        return userService.checkEmailAva(email);
    }

    public void accessDeniedPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/views/accessDenied.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession(true);
        Object roll = session.getAttribute("type");
        if(roll == null) {
            accessDeniedPage(request, response);
            return;
        }
        String message = new String();
        User U = new User();
        U.setId((int)session.getAttribute("userID"));
        U.setFirstName(request.getParameter("firstName"));
        U.setLastName(request.getParameter("lastName"));
        U.setEmail(request.getParameter("email"));
        U.setUsername(request.getParameter("username"));
        U.setContact(request.getParameter("contact"));
        U.setCountry(request.getParameter("country"));
        U.setZipCode(request.getParameter("zipCode"));
        U.setPassword(request.getParameter("password"));
        if(userService.updateUser(U) >= 0) {
            message = "Update Successful";
            if(U.getLastName() != null){session.setAttribute("lname", U.getLastName());}
            if(U.getFirstName() != null){session.setAttribute("fname", U.getFirstName());}
            if(U.getUsername() != null){session.setAttribute("username", U.getUsername());}
            if(U.getEmail() != null){session.setAttribute("email", U.getEmail());}
            if(U.getContact() != null){session.setAttribute("contact", U.getContact());}
            if(U.getCountry() != null){session.setAttribute("country", U.getCountry());}
            if(U.getZipCode() != null){session.setAttribute("zipcode", U.getZipCode());}
            session.setAttribute("name",  session.getAttribute("fname") + " " + session.getAttribute("lname"));
        }
        else
            message = "Update Unsuccessful";

        request.setAttribute("message", message);
        try {
            request.getRequestDispatcher("/views/viewProfile.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
