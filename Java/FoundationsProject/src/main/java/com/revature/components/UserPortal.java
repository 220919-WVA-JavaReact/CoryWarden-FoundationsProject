package com.revature.components;

import java.util.Scanner;

public class UserPortal {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Users cory = new Users("Cory", "Warden", "12cwarden@gmail.com", "admin", "test");
        System.out.println("My role is: " + cory.getRole());
        String role = cory.getRole();


        System.out.println("Welcome to the user portal, " + cory.getfName()); //import username and add name to welcome.
        System.out.println("-------------------------------------------------");

        if (role.equals("Employee")) {
            System.out.println("You are an employee");
            System.out.println("What would you like to do?");
            System.out.println("S) Submit a new reimbursement request");
            System.out.println("V) View all previous reimbursement requests");
            System.out.println("L) Log out");
            String option1 = scan.nextLine();

            if (option1.equals("S") || option1.equals("s")) {
                System.out.println("Opening Reimbursement form");
                System.out.println("-----------------------------");
                System.out.println("How much was your work expense?");
                float amount = scan.nextFloat();
                scan.nextLine();
                System.out.println("Please give a description of the expense reason");
                String description = scan.nextLine();

                //Store ticket to database.
                Tickets newTick = new Tickets(cory.getUsername(), cory.getId(), amount, description);
                System.out.println( newTick.getId() + " " + newTick.getAmount() + " " + newTick.getDescription() + " " + newTick.getAuthId() + " " + newTick.getStatus());
            } else if (option1.equals("V") || option1.equals("v")) {
                //View all submitted tickets?
                System.out.println("Figure out way to pull tickets from database.");
            } else if (option1.equals("L") || option1.equals("l")) {
                System.out.println("Thank you for using the user portal!");
                //logout;
            }

        } else if (role.equals("Manager")) {
            System.out.println("You are a manager");
            System.out.println("What would you like to do?");
            System.out.println("V) View all reimbursement requests with Pending status");
            System.out.println("H) View all reimbursement requests in the system");
            System.out.println("L) Log out");
            String option2 = scan.nextLine();

            if (option2.equals("V") || option2.equals("v")) {
                //show all database entries equaling "Pending"
                System.out.println("1");
            } else if (option2.equals("H") || option2.equals("h")) {
                //show all database entries with approved/deny/pending
                System.out.println("2");
            } else if (option2.equals("L") || option2.equals("l")) {
                //logout
                System.out.println("Thank you for using the user portal!");
            }
        } else {
            System.out.println("How did you make it through? You are neither");
        }
    }
}
