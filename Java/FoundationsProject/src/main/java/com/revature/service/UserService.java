package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    UserDAO ud = new UserDaoJDBC();
    ReimbursementService rs = new ReimbursementService();
    Scanner scan = new Scanner(System.in);

    public User login() {
        //Main Menu
        System.out.println("Login------------");
        System.out.println("Please enter your username");
        String username = scan.nextLine();
        System.out.println("Please enter your password");
        String password = scan.nextLine();
        //Get the account associated with username and verify the password to log the user in.
        /*
        1) Call Database and find info based off submitted username
        2) Check returned pw of username in database against the input to verify they are equal
         */
        User loggedInUser = ud.getByUsername(username.toLowerCase());
        if (loggedInUser.getPw().equals(password)) {
            System.out.println("+-------------------------------------+");
            return loggedInUser;
        } else {
            System.out.println("You do not have an account in our database.");
            return null;
        }
    }

    public User register () {
        //Prompt register questions;
        System.out.println("Register------------");
        System.out.println("Please enter your first name");
        String fName = scan.nextLine();
        System.out.println("Please enter your last name");
        String lName = scan.nextLine();
        System.out.println("Please enter your email");
        String email = scan.nextLine();
        System.out.println("Please enter your username");
        String username = scan.nextLine();
        System.out.println("Please enter your password");
        String pw = scan.nextLine();


        //------------------Add some implementation to check if username/email already exists?
        User newUser = new User(fName, lName, email, username, pw);
        //add user to the database and return new user.
        return ud.addUser(newUser);
    }

    public void portal(User loggedInUser) {
        String userRole = loggedInUser.getRole();
        if (userRole.equals("employee")) {
            System.out.println("Welcome, " + loggedInUser.getfName() +"!");
            System.out.println("What would you like to do?");
            System.out.println("1) View your tickets");
            System.out.println("2) Submit a new ticket");
            System.out.println("3) Logout");
            int userChoice = scan.nextInt();
            scan.nextLine();

            //Intake Author ID to get list of reimbursement tickets, or to store for new reimbursement ticket.
            int authId = loggedInUser.getId();
            if (userChoice == 1) {
                rs.viewPersonalTickets(authId);
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else if (userChoice == 2) {
                rs.addReimbursement(loggedInUser.getUsername(), loggedInUser.getId());
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else {
                System.out.println("Option not eligible.");
            }
        } else if (userRole.equals("manager")) {
            System.out.println("Welcome, " + loggedInUser.getfName() +"!");
            System.out.println("What would you like to do?");
            System.out.println("1) View all pending tickets");
            System.out.println("2) View all approved tickets");
            System.out.println("3) View all denied tickets");
            System.out.println("4) Change role of employee");
            System.out.println("5) Logout");
            int userChoice = scan.nextInt();
            scan.nextLine();

            //Get tickets by status choice entered when choosing above
            if (userChoice == 1) {
                String status = "Pending";
                ReimbursementService.getTicketsByStatus(status);
                rs.statusChange();
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else if (userChoice == 2) {
                String status = "Approved";
                ReimbursementService.getTicketsByStatus(status);
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else if (userChoice == 3) {
                String status = "Denied";
                ReimbursementService.getTicketsByStatus(status);
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else if (userChoice == 4) {
                promoteUser();
                System.out.println("+----------------------------------------------------+");
                portal(loggedInUser);
            } else if (userChoice == 5) {
                System.out.println("Now logging out.");
            } else {
                System.out.println("Option not eligible.");
            }
        } else {
            System.out.println("You do not have an established role. Please get with your manager.");
        }
        //return loggedInUser;
    }

    //run new method to view all users
    public void getAllUsers() {
        System.out.println("Using the database to return all users");
        List<User> userList = ud.getAllUsers();

        //cycle through with enhanced for loop to print all database rows
        for (User user : userList) {
            System.out.println("ID: " + user.getId() + " || Name: " + user.getfName() + " " + user.getlName() +
                    " || Email: " + user.getEmail() + " || Username: " + user.getUsername() + " || Role: " + user.getRole());
        }
    }

    public void promoteUser() {
        getAllUsers();
        System.out.println("Above are all users-------");
        System.out.println("Please enter the username of who you would like to switch the role of");
        String promoted = scan.nextLine();
        //Relay user information based off of username intake
        User u = ud.getByUsername(promoted);
        System.out.println("What would you like their new position to be?");
        System.out.println("1) Manager");
        System.out.println("2) Employee");
        //Intake response on which integer to change in updateRole
        int newRole = scan.nextInt();
        scan.nextLine();

        //update role based off of username and integer response
        ud.updateRole(u, newRole);

    }
}
