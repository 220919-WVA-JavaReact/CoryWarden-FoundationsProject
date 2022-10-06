package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    UserDAO ud = new UserDaoJDBC();
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
            System.out.println("You are an existing user.");
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
        User loggedInUser = ud.addUser(newUser);
        return loggedInUser;
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
}
