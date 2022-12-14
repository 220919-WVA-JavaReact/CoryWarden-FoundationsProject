package com.revature.servlets;

import com.revature.controller.ReimbursementControllers;
import com.revature.controller.UserControllers;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

    public UserControllers uc = new UserControllers();
    public ReimbursementControllers rc = new ReimbursementControllers();

    @Override
    public void init() {
        System.out.println("[LOG] - Servlet Instantiated--------------------");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());

        if (urlPath.startsWith("/u/")) {
            //uc.functionGet(req, resp); -- deleted from UserControllers.
        } else if (urlPath.startsWith(("/r/"))) {
            rc.functionGet(req , resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());

        if (urlPath.startsWith("/u/")) {
            uc.functionPost(req, resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/users POST correctly.");
        } else if (urlPath.startsWith(("/r/"))) {
            rc.functionPost(req , resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/reimbursements POST correctly.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        //get direct path of request
        String urlPath = req.getRequestURI().substring(req.getContextPath().length());

        if (urlPath.startsWith("/u/")) {
            uc.functionPut(req, resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/users POST correctly.");
        } else if (urlPath.startsWith(("/r/"))) {
            rc.functionPut(req , resp);
            //check that process is hitting to this point
            //resp.getWriter().write("You are reaching /foundations-project/reimbursements POST correctly.");
        }
    }
}
