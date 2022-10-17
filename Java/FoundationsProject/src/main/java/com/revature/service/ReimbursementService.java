package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.models.Reimbursement;
import java.io.IOException;
import java.util.List;

public class ReimbursementService {
    static ReimbursementDAO rd = new ReimbursementDaoJDBC();

//    public void viewPersonalTickets(int authId) throws IOException, ClassNotFoundException {
//        System.out.println("Using the database to return all tickets for you");
//        List<Reimbursement> userTickets = rd.getByReimbursementAuth(authId);
//
//        //cycle through with enhanced for loop to print all database rows
//        for (Reimbursement ticket : userTickets) {
//            //Create formatted ticket, so it's not in raw format.
//            String formattedAmount = String.format("%.02f", ticket.getAmount());
//            System.out.println("Ticket ID: " + ticket.getId() + " || Status: " + ticket.getStatus() + " || Username: " + ticket.getUsername() +
//                    " || AuthorId: " + ticket.getAuthId() + " || Amount: $" + formattedAmount + " || Description: " + ticket.getDescription());
//        }
//    }

    public List<Reimbursement>getTicketsByStatus(String status) {
        return rd.getTicketsByStatus(status);
    }

    public Reimbursement getByTicketId (int id) throws IOException, ClassNotFoundException {
        return rd.getByTicketId(id);
    }

    public List<Reimbursement>getByReimbursementAuth(int authId) throws IOException, ClassNotFoundException {
        return rd.getByReimbursementAuth(authId);
    }

    public List<Reimbursement>getAllTickets() {
        return rd.getAllTickets();
    }

    public Reimbursement statusChange(String status, int ticketId) {
        return rd.updateStatus(status, ticketId);
    }

    public Reimbursement addReimbursement (Reimbursement r) {
        return rd.addReimbursement(r);
    }

}
