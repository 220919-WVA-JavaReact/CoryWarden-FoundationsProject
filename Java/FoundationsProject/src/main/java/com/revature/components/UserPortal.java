package com.revature.components;

import java.util.Scanner;

public class UserPortal {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Users cory = new Users("Cory", "Warden", "12cwarden@gmail.com", "admin", "test");
        System.out.println("My role is: " + cory.role);
        String role = cory.role;


        System.out.println("Welcome to the user portal, " + cory.fName); //import username and add name to welcome.
        System.out.println("-------------------------------------------------");

        if (role.equals("Employee")) {
            System.out.println("You are an employee");
            System.out.println("What would you like to do?");
            System.out.println("S) Submit a new reimbursement request");
            System.out.println("V) View all previous reimbursement requests");
            System.out.println("L) Log out");
            String option1 = scan.nextLine();

            if (option1.equals("S")) {
                System.out.println("Opening Reimbursement form");
                System.out.println("-----------------------------");
                System.out.println("How much was your work expense?");
                float amount = scan.nextFloat();
                scan.nextLine();
                System.out.println("Please give a description of the expense reason");
                String description = scan.nextLine();

                //Store ticket to database.
                Tickets newTick = new Tickets(cory.username, cory.id, amount, description);
                System.out.println(newTick.amount + " " + newTick.description);
            } else if (option1.equals("V")) {
                //View all submitted tickets?
                System.out.println("Figure out way to pull tickets from database.");
            } else if (option1.equals("L")) {
                System.out.println("Thank you for using the user portal!");
                //logout;
            }

        } else if (role.equals("Manager")) {
            System.out.println("You are a manager");
        } else {
            System.out.println("How did you make it through? You are neither");
        }
    }
}
