package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.IOException;
import java.util.List;

public interface ReimbursementDAO {

    //get by reimbursements by user
    List<Reimbursement> getByReimbursementAuth (int authId) throws IOException, ClassNotFoundException;

    Reimbursement getByTicketId (int id) throws IOException, ClassNotFoundException;

    //get by reimbursement ticket id
    List<Reimbursement> getTicketsByStatus (String status);

    List<Reimbursement> getAllTickets ();

    //add new reimbursement to database
    Reimbursement addReimbursement (Reimbursement r);


    //approve or deny
    Reimbursement updateStatus(String status, int ticketId);

}
