package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ReimbursementControllers {

    ObjectMapper mapper = new ObjectMapper();
    public ReimbursementDAO rd = new ReimbursementDaoJDBC();

    public void functionGet(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path

        if (urlPath.equals("/r/tickets")) {
            //Load userTicket below using method from ReimbursDaoJDBC
            userTickets(req, resp);
        }
    }

    public void userTickets(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a VIEWTICKETS GET req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    User u = (User) session.getAttribute("user");

                    List<Reimbursement> userTickets = rd.getByReimbursementAuth(u.getId());
                    //write object as string and return
                    String jsonTickets = mapper.writeValueAsString(userTickets);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonTickets);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
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
