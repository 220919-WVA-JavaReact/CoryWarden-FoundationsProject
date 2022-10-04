package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOCSV;
import com.revature.models.Reimbursement;

public class ReimbursementService {

    static ReimbursementDAO rd = new ReimbursementDAOCSV();

    public static void viewPersonalTickets(int id) {
        Reimbursement rt = rd.getByReimbursementAuth(id);
        if (rt.getAuthId() == id) {
            System.out.println(rt);
        } else {
            System.out.println("This ticket does not exist");
        }
    }

}
