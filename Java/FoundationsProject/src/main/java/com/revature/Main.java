package com.revature;

import com.revature.models.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;

import java.util.Scanner;

public class Main {

    public static UserService us = new UserService();
    public static ReimbursementService rs = new ReimbursementService();
    public static void main(String[] args) {

        System.out.println("Welcome! Would you like to register or login?");
        System.out.println("1) Login");
        System.out.println("2) Register");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.nextLine();

        User loggedInUser = null;

        if (choice == 1) {
            //Call LOGIN logic from UserService
            loggedInUser = us.login();
        } else if (choice == 2) {
            //Call REGISTER logic from UserService
            loggedInUser = us.register();
        } else {
            System.out.println("Incorrect input");
        }

        if (loggedInUser != null) {
            String userRole = loggedInUser.getRole();
            if (userRole.equals("employee")) {
                System.out.println("What would you like to do?");
                System.out.println("1) View your tickets");
                System.out.println("2) Submit a new ticket");
                System.out.println("3) Logout");
                int userChoice = scan.nextInt();
                scan.nextLine();

                int authId = loggedInUser.getId();
                if (userChoice == 1) {
                    ReimbursementService.viewPersonalTickets(authId);
                } else if (userChoice == 2) {
                  rs.addReimbursement(loggedInUser.getUsername(), loggedInUser.getId());
                } else {
                    System.out.println("Option not eligible.");
                }
            } else if (userRole.equals("manager")) {
                System.out.println("What would you like to do?");
                System.out.println("1) View all pending tickets");
                System.out.println("2) View all approved tickets");
                System.out.println("3) View all denied tickets");
                System.out.println("4) Logout");
                int userChoice = scan.nextInt();
                scan.nextLine();

                if (userChoice == 1) {
                    String status = "Pending";
                    ReimbursementService.viewAllTickets(status);
                    rs.statusChange();
                } else if (userChoice == 2) {
                    String status = "Approved";
                    ReimbursementService.viewAllTickets(status);
                } else if (userChoice == 3) {
                    String status = "Denied";
                    ReimbursementService.viewAllTickets(status);
                } else if (userChoice == 4) {
                    System.out.println("Now logging out.");
                } else {
                    System.out.println("Option not eligible.");
                }
            } else {
                System.out.println("You do not have an established role. Please get with your manager.");
            }
        }
    }
}