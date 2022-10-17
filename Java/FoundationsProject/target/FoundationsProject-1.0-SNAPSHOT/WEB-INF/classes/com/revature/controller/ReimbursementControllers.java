package com.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReimbursementControllers {

    ObjectMapper mapper = new ObjectMapper();
    public ReimbursementService rs = new ReimbursementService();

    public void functionGet(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path

        if (urlPath.equals("/r/tickets")) {
            //Load userTicket below using method from ReimbursDaoJDBC
            userTickets(req, resp);
        } else if (urlPath.equals("/r/pendingtickets")) {
            pendingTickets(req, resp);
        } else if (urlPath.equals("/r/alltickets")) {
            //Load userTicket below using method from ReimbursDaoJDBC
            allTickets(req, resp);
        }
    }
    public void functionPost(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path

        if (urlPath.equals("/r/newticket")) {
            //Load userTicket below using method from ReimbursDaoJDBC
            newTicket(req, resp);
        }
    }

    public void functionPut(HttpServletRequest req, HttpServletResponse resp) {

        String urlPath = req.getRequestURI().substring(req.getContextPath().length()); //grab url path

        if (urlPath.equals("/r/approveticket")) {
            //Load ticketstatus below using method from ReimbursDaoJDBC
            approveTicket(req, resp);
        } else if (urlPath.equals("/r/denyticket")) {
            //Load ticketstatus below using method from ReimbursDaoJDBC
            denyTicket(req, resp);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////FUNCTIONS//////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////MANAGER ALL TICKETS/////////////////////////////////////////////////////////
    private void allTickets(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a VIEWALL GET req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().equals("manager")) {
                try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    List<Reimbursement> allTickets = rs.getAllTickets();
                    //write object as string and return
                    String jsonTickets = mapper.writeValueAsString(allTickets);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonTickets);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                resp.setStatus(403);
                try {
                    resp.getWriter().write("Unable to access all tickets as an employee.");
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

    /////////////////////////////////////MANAGER PENDING TICKETS///////////////////////////////////////////////////////
    private void pendingTickets(HttpServletRequest req, HttpServletResponse resp) {
        //System.out.println("[LOG2] - Sanity Servlet received a VIEWPENDING GET req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().equals("manager")) {
                try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    List<Reimbursement> allTickets = rs.getTicketsByStatus("pending");
                    //write object as string and return
                    String jsonTickets = mapper.writeValueAsString(allTickets);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(jsonTickets);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                resp.setStatus(403);
                try {
                    resp.getWriter().write("Unable to access all tickets as an employee.");
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

    ///////////////////////////////////MANAGER CHANGE TICKET STATUS////////////////////////////////////////////////////
    private void approveTicket(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a UPDATETICKET PUT req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().equals("manager")) {
                try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
                    Reimbursement ticket = rs.getByTicketId(r.getId());
                    if (ticket.getStatus().equals("pending")) {
                        Reimbursement updated = rs.statusChange("approved", r.getId());
                        //write object as string and return
                        String jsonTickets = mapper.writeValueAsString(updated);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(jsonTickets);
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("Ticket has already been processed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resp.setStatus(403);
                try {
                    resp.getWriter().write("Unable to change tickets due to role status or ticket status.");
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

    private void denyTicket(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("[LOG2] - Sanity Servlet received a UPDATETICKET PUT req at " + LocalDateTime.now());

        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().equals("manager")) {
                try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
                    Reimbursement ticket = rs.getByTicketId(r.getId());
                    if (ticket.getStatus().equals("pending")) {
                        Reimbursement updated = rs.statusChange("denied", r.getId());
                        //write object as string and return
                        String jsonTickets = mapper.writeValueAsString(updated);
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(jsonTickets);
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("Ticket has already been processed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resp.setStatus(403);
                try {
                    resp.getWriter().write("Unable to change tickets due to role status or ticket status.");
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

    ///////////////////////////////////////EMPLOYEE NEW TICKET/////////////////////////////////////////////////////////
    private void newTicket(HttpServletRequest req, HttpServletResponse resp) {
        //System.out.println("[LOG2] - Sanity Servlet received a NEWTICKET GET req at " + LocalDateTime.now());

        HttpSession session = req.getSession(false);
        if (session != null) {
            User u = (User) session.getAttribute("user");
            try {
                //create user using input from Postman
                Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
                //run through UserDAOJDBC register method
                //TODO add validation for amount. Unsure how to check if float
                if (u.getUsername().equals(r.getUsername())) {
                    if (!r.getDescription().trim().equals("") && r.getDescription() != null && r.getAmount() != 0.0f) {
                        Reimbursement newReimbursement = rs.addReimbursement(r);
                        //write object as string
                        String jsonTicket = mapper.writeValueAsString(newReimbursement);
                        //feed responses back through postman
                        resp.setStatus(200);
                        resp.setContentType("application/json");
                        resp.getWriter().write(jsonTicket);
                    } else {
                        //return error stating that description or amount is incorrect
                        resp.setStatus(400);
                        resp.getWriter().write("Unable to add ticket. Please check your description and amount.");
                    }
                } else {
                    //return error code advising unable to add.
                    resp.setStatus(400);
                    resp.getWriter().write("Unable to add ticket. Username does not match ticket author.");
                }
            } catch (IOException e) {
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

    ///////////////////////////////////////EMPLOYEE USER TICKETS///////////////////////////////////////////////////////
    public void userTickets(HttpServletRequest req, HttpServletResponse resp) {
        //ensure session is grabbed and is not null
        HttpSession session = req.getSession(false);
        //System.out.println(session.getId());

        if (session != null) {
            try {
                    //create list of tickets from method in ReimbursDaoJDBC and parsing as an integer to use argument
                    User u = (User) session.getAttribute("user");
                    List<Reimbursement> userTickets = rs.getByReimbursementAuth(u.getId());
                    //write object as string and return
                    String jsonTickets = mapper.writeValueAsString(userTickets);
                //feed responses back through postman
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
