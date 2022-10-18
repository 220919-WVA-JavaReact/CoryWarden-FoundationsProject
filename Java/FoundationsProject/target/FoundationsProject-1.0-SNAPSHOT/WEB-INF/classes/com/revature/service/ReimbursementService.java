package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.models.Reimbursement;
import java.io.IOException;
import java.util.List;

public class ReimbursementService {
    static ReimbursementDAO rd = new ReimbursementDaoJDBC();

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
