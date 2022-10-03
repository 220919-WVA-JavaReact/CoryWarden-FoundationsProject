package com.revature.components;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        //establish variables for user.
        Scanner scan = new Scanner(System.in);

        //Start login input
        System.out.println("Welcome! Would you like to register or login? (register/login)");
        String input = scan.nextLine();

        if (input.equals("register")) {
            //Prompt register questions;
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

            //Add some implementation to check if username/email already exists?
            System.out.println("Success! You are now able to login.");

            //If user does not exist, store to sql database.
            Users userNew = new Users(fName, lName, email, username, pw);
            System.out.println(userNew.username);
        } else if (input.equals("login")) {
            //Give login screen
            System.out.println("Please enter your username");
            String userLogin = scan.nextLine();
            System.out.println("Please enter your password");
            String userPw = scan.nextLine();

            //Implement feature to cross check against database and deny users that don't exist/have wrong info
            System.out.println("Login successful!");
        } else {
            System.out.println("Your input was invalid. Please try again later.");
        }
    }
}
