package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {

    //get by reimbursements by user
    Reimbursement getByReimbursementAuth (int authId);

    //get by reimbursement ticket id
    Reimbursement getByTicketId (int id);

    //add new reimbursement to database
    Reimbursement addReimbursement (String username, int authId, float amount, String description);

    //get all reimbursements for manager view?
    Reimbursement getByReimbursementStatus (String status);

    //approve or deny
    boolean changeStatus(Reimbursement reimbursement);

}
