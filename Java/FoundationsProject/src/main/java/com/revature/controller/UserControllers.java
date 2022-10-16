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

//        if (urlPath.equals("/u/logout")) {
//            //loads logout below
//            logout(req, resp);
//        }
    }

    public void functionPut(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path
        //System.out.println(urlPath);

        if (urlPath.equals("/u/updaterole")) {
            //loads updaterole below using UserDAOJDBC
            updateRole(req,resp);
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
        } else if (urlPath.equals("/u/logout")) {
            //loads logout below
            logout(req, resp);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////FUNCTIONS//////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////USER LOGIN/////////////////////////////////////////////////////////////
    public void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //accept params from Postman and read
            User u = mapper.readValue(req.getInputStream(), User.class);
            //run through UserDAOJDBC register method
            User actualUser = us.login(u);
            //write object as a string
            String jsonUser = mapper.writeValueAsString(actualUser);

            if (actualUser != null) {
                //bind the object to session
                req.getSession().setAttribute("user", actualUser);
                resp.setStatus(200);
                resp.setHeader("username", actualUser.getUsername());
                resp.setHeader("userid", String.valueOf(actualUser.getId()));
                resp.getWriter().write(jsonUser);
            } else {
                //return error code advising unable to add.
                resp.setStatus(400);
                resp.getWriter().write("Invalid credentials. Please try again.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////USER LOGOUT/////////////////////////////////////////////////////////////
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);

        //check if session is not null, then invalidate from list
        if (session != null) {
            //give response code and feedback.
            session.invalidate();
            resp.setStatus(200);
            try {
                resp.getWriter().write("You are now logged out.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setStatus(403);
            try {
                resp.getWriter().write("You are not logged in.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /////////////////////////////////////////USER REGISTER/////////////////////////////////////////////////////////////
    public void register(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //create user using input from Postman
            User u = mapper.readValue(req.getInputStream(), User.class);
            //run through UserDAOJDBC register method
            User actualUser = us.register(u);
            //write object as string
            String jsonUser = mapper.writeValueAsString(actualUser);

            if (actualUser != null) {
                //bind the object to session
                req.getSession().setAttribute("user", actualUser);
                resp.setStatus(200);
                resp.setHeader("username", actualUser.getUsername());
                resp.setHeader("userid", String.valueOf(actualUser.getId()));
                resp.setContentType("application/json");
                resp.getWriter().write(jsonUser);
            } else {
                //return error code advising unable to add.
                resp.setStatus(400);
                resp.getWriter().write("Unable to add user. Already exists in database.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////MANAGER ROLE UPDATE/////////////////////////////////////////////////////////
    private void updateRole(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a UPDATETICKET PUT req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().equals("manager")) {
                try {
                    //Grab updated user from the username/role Postman input
                    User updateUser = mapper.readValue(req.getInputStream(), User.class);
                    //if the input user is not equal to requesting manager, process below
                    if (!updateUser.getUsername().equals(u.getUsername())) {
                        User updated = us.promoteUser(updateUser, updateUser.getRole());
                        //write object as string and return
                        String jsonTickets = mapper.writeValueAsString(updated);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(jsonTickets);
                    } else {
                        resp.setStatus(403);
                        resp.getWriter().write("Unable to change your own role.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //if input user is equal to requesting manager, advise unable to change your own role.
                resp.setStatus(403);
                try {
                    resp.getWriter().write("Manager only feature. Unable to access.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            resp.setStatus(401);
            try {
                resp.getWriter().write("You are not logged in. Unable to access tickets.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
