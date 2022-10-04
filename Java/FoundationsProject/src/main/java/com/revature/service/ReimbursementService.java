package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOCSV;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import java.util.Scanner;

public class ReimbursementService {
    Scanner scan = new Scanner(System.in);
    static ReimbursementDAO rd = new ReimbursementDAOCSV();

    public static void viewPersonalTickets(int id) {
        Reimbursement rt = rd.getByReimbursementAuth(id);
        if (rt.getAuthId() == id) {
                System.out.println(rt);
        } else {
            System.out.println("This ticket does not exist");
        }
    }

    public static void viewAllTickets(String status) {
        Reimbursement rt = rd.getByReimbursementStatus(status);
        if (rt.getStatus().equals(status)) {
            System.out.println(rt);
        } else {
            System.out.println("Error, not found.");
        }
    }

    public void statusChange() {
        System.out.println("Enter ticket number: ");
        int ticketId = scan.nextInt();
        scan.nextLine();
        Reimbursement rt = rd.getByTicketId(ticketId);
        System.out.println(rt);
        //Approve or Deny above ticket
        System.out.println("Would you like to Approve or Deny?");
        System.out.println("1) Approve");
        System.out.println("2) Deny");
        int choice = scan.nextInt();
        scan.nextLine();
        //If statement to choose which way to update status field
        if (choice == 1) {
            System.out.println("Ticket has been approved.");
            Reimbursement approved = new Reimbursement(ticketId, "Approved");
            rd.changeStatus(approved);
            System.out.println(rt);
        } else if (choice == 2) {
            System.out.println("Ticket has been denied");
            Reimbursement denied = new Reimbursement(ticketId, "Denied");
            rd.changeStatus(denied);
            System.out.println(rt);
        } else {
            System.out.println("Choice not valid.");

        }
    }

    public void addReimbursement (String username, int authId) {
        //Prompt register questions;
        System.out.println("New Reimbursement------------");
        System.out.println("Please enter the reimbursement amount");
        float amount = scan.nextFloat();
        scan.nextLine();
        System.out.println("Please enter a description for this amount");
        String description = scan.nextLine();


        //Add some implementation to check if username/email already exists?
        System.out.println("Success! You are now able to login.");
        rd.addReimbursement(username, authId, amount, description);
        System.out.println(username + " " + authId + " " + amount + " " + description); //verify uname and authId
    }

}
