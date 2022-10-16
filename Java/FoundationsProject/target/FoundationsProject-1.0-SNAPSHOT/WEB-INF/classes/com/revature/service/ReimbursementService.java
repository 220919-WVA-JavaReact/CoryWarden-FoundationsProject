package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDaoJDBC;
import com.revature.models.Reimbursement;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReimbursementService {
    Scanner scan = new Scanner(System.in);
    static ReimbursementDAO rd = new ReimbursementDaoJDBC();

    public void viewPersonalTickets(int authId) throws IOException, ClassNotFoundException {
        System.out.println("Using the database to return all tickets for you");
        List<Reimbursement> userTickets = rd.getByReimbursementAuth(authId);

        //cycle through with enhanced for loop to print all database rows
        for (Reimbursement ticket : userTickets) {
            //Create formatted ticket, so it's not in raw format.
            String formattedAmount = String.format("%.02f", ticket.getAmount());
            System.out.println("Ticket ID: " + ticket.getId() + " || Status: " + ticket.getStatus() + " || Username: " + ticket.getUsername() +
                    " || AuthorId: " + ticket.getAuthId() + " || Amount: $" + formattedAmount + " || Description: " + ticket.getDescription());
        }
    }

    public List<Reimbursement>getTicketsByStatus(String status) {
        return rd.getTicketsByStatus(status);
    }

    public List<Reimbursement>getByReimbursementAuth(int authId) throws IOException, ClassNotFoundException {
        return rd.getByReimbursementAuth(authId);
    }

    public List<Reimbursement>getAllTickets() {
        return rd.getAllTickets();
    }

    public Reimbursement statusChange(String status, int ticketId) {
//        System.out.println("Enter ticket number: ");
//        int ticketId = scan.nextInt();
//        scan.nextLine();
//        System.out.println("Would you like to Approve or Deny?");
//        System.out.println("1) Approve");
//        System.out.println("2) Deny");
//        System.out.println("3) Return to main menu");
//        int choice = scan.nextInt();
//        scan.nextLine();
//        //If statement to choose which way to update status field
//        if (choice == 1) {
//            System.out.println("Ticket has been approved.");
//            rd.updateStatus("Approved", ticketId);
//        } else if (choice == 2) {
//            System.out.println("Ticket has been denied");
//            rd.updateStatus("Denied", ticketId);
//        } else if (choice == 3) {
//            System.out.println("Returning to main menu.");
//        } else {
//            System.out.println("Choice not valid.");
//
//        }
        return rd.updateStatus(status, ticketId);
    }

    public Reimbursement addReimbursement (Reimbursement r) {
        return rd.addReimbursement(r);
    }

}
