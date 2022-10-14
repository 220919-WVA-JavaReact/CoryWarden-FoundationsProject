package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.ReimbursementControllers;
import com.revature.controller.UserControllers;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class Servlet extends HttpServlet {

    public UserService us = new UserService();
    public UserControllers uc = new UserControllers();
    public ReimbursementControllers rc = new ReimbursementControllers();

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - Servlet Instantiated--------------------");
    }

    ObjectMapper mapper  = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());
        //resp.getWriter().write("You are reaching /foundations-project correctly. ");

        if (urlPath.startsWith("/u/")) {
            uc.functionGet(req, resp);
            //check that process is hitting to this point
            resp.getWriter().write("You are reaching /foundations-project/users GET correctly.");
        } else if (urlPath.startsWith(("/r/"))) {
            rc.functionGet(req , resp);
            //check that process is hitting to this point
            resp.getWriter().write("You are reaching /foundations-project/reimbursements GET correctly.");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());

        if (urlPath.startsWith("/u/")) {
            uc.functionPost(req, resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/users POST correctly.");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());

        if (urlPath.startsWith("/u/")) {
            uc.functionPut(req, resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/users POST correctly.");
        }
    }
}
