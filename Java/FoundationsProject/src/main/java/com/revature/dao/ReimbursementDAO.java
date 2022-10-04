package com.revature.dao;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {

    //get by reimbursement id number
    Reimbursement getByReimbursementAuth (int id);

    //add new reimbursement to database
    //get all reimbursements from user?
    //approve or deny
    boolean changeStatus(String choice, int id, String status);

}
