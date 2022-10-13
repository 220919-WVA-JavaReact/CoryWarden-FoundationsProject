package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserControllers {

    public UserService us = new UserService();
    ObjectMapper mapper  = new ObjectMapper();

    public void functionGet(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path
        //System.out.println(urlPath);

        if (urlPath.equals("/u/logout")) {
            //loads logout below
            logout(req, resp);
        }
    }

    public void functionPost(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path
        //System.out.println(urlPath);

        if (urlPath.equals("/u/login")) {
            //loads login below using method from UserDAOJDBC
            login(req, resp);
        } else if (urlPath.equals("/u/register")) {
            //loads register below using method from UserDAOJDBC
            register(req,resp);
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) {
        //check that process is hitting to this point
        System.out.println("[LOG2] - Sanity Servlet received a LOGIN POST req at " + LocalDateTime.now());
        try {
            //accept params from Postman and read
            User u = mapper.readValue(req.getInputStream(), User.class);
            //run through UserDAOJDBC register method
            User actualUser = us.login(u);
            //write object as a string
            String jsonUser = mapper.writeValueAsString(actualUser);

            if (actualUser != null) {
                //bind the object to session
                req.getSession().setAttribute("user", actualUser.getId());
                resp.setHeader("user", actualUser.getUsername());
                resp.getWriter().write(jsonUser);
            } else {
                //return error code advising unable to add.
                resp.setStatus(400);
                resp.getWriter().write("No user found.");
                System.out.println("No user found.");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        //check that process is hitting to this point
        System.out.println("[LOG2] - Sanity Servlet received a LOGOUT GET req at " + LocalDateTime.now());
        //bind session to current session
        HttpSession session = req.getSession();

        //check if session is not null, then invalidate from list
        if (session != null) {
            session.invalidate();
        }
        //give response code and sysout.
        resp.setStatus(204);
        System.out.println("You are now logged out.");
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a REGISTER POST req at " + LocalDateTime.now());

        try {
            //create user using input from Postman
            User u = mapper.readValue(req.getInputStream(), User.class);
            //run through UserDAOJDBC register method
            User actualUser = us.register(u);
            //write object as string
            String jsonUser = mapper.writeValueAsString(actualUser);

            if (actualUser != null) {
                //bind the object to session
                req.getSession().setAttribute("user", actualUser.getId());
                resp.setContentType("application/json");
                resp.getWriter().write(jsonUser);
            } else {
                //return error code advising unable to add.
                resp.setStatus(400);
                resp.getWriter().write("Unable to add user.");
                System.out.println("Unable to add user.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
