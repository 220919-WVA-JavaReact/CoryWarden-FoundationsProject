package com.revature;

import com.revature.components.Users;
import com.revature.service.UserService;

import java.util.Scanner;

public class Main {

    public static UserService us = new UserService();
    public static void main(String[] args) {

        System.out.println("Press 1 to login. Press 2 to register");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        scan.nextLine();

        if (choice == 1) {
            //This is where we get login
            System.out.println("Login------------");
            System.out.println("Please enter your username");
            String username = scan.nextLine();
            System.out.println("Please enter your password");
            String password = scan.nextLine();

            //At this point, call TeacherService Class to handle logic for signing in
            us.login(username, password);
        } else if (choice == 2) {
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

            //Add some implementation to check if username/email already exists?
            System.out.println("Success! You are now able to login.");
        } else {
            System.out.println("Incorrect input");
        }
    }
}