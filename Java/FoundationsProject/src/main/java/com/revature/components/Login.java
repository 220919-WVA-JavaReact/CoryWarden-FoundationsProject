package com.revature.components;

import java.util.Scanner;
import com.revature.components.UserPortal;

public class Login {
    private static UserPortal userPortal = new UserPortal();
    public static void main(String[] args) {
        //establish variables for user.
        Scanner scan = new Scanner(System.in);

        //Start login input
        System.out.println("Welcome! Would you like to register or login?");
        System.out.println("R) Register");
        System.out.println("L) Login");

        String input = scan.nextLine();

        if (input.equals("R") || input.equals("r")) {
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
            System.out.println(userNew.getUsername());
        } else if (input.equals("L") || input.equals("l")) {
            //Give login screen
            System.out.println("Please enter your username");
            String userLogin = scan.nextLine();
            System.out.println("Please enter your password");
            String userPw = scan.nextLine();

            //Implement feature to cross-check against database and deny users that don't exist/have wrong info

            System.out.println("Login successful!");
            userPortal.main(new String[] {userLogin});
        } else {
            System.out.println("Your input was invalid. Please try again later.");
        }
    }
}
