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
        //System.out.println("3) Print users");
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
            //System.out.println(loggedInUser); --Checking where user is losing role
        } else {
           // System.out.println("Incorrect input");
            us.getAllUsers();
        }

        if (loggedInUser != null) {
            //User portal for main menu
            us.portal(loggedInUser);
        }
    }
}