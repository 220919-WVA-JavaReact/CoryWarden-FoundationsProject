package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {

    UserDAO ud = new UserDaoJDBC();
    Scanner scan = new Scanner(System.in);

    public User login(User loggedInUser) throws SQLException {
        //validate user authorization with username and pw
        return ud.getAuth(loggedInUser.getUsername(), loggedInUser.getPw());
    }

    public User register (User newUser) {
        //add user to the database and return new user.
        return ud.addUser(newUser);
    }

//    public void portal(User loggedInUser) throws IOException, ClassNotFoundException {
//        return loggedInUser;
//    }

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

    public User promoteUser(User loggedInUser, String status) {
//        getAllUsers();
//        System.out.println("Above are all users-------");
//        System.out.println("Please enter the username of who you would like to switch the role of");
//        String promoted = scan.nextLine();
//        //Relay user information based off of username intake
//        User u = ud.getByUsername(promoted);
//        if (u.getUsername().equals(loggedInUser.getUsername())) {
//            System.out.println("You cannot change your own status.");
//        } else {
//            System.out.println("What would you like their new position to be?");
//            System.out.println("1) Manager");
//            System.out.println("2) Employee");
//            //Intake response on which integer to change in updateRole
//            int newRole = scan.nextInt();
//            scan.nextLine();
//
//            //update role based off of username and integer response
//            ud.updateRole(u, newRole);
//        }

        return ud.updateRole(loggedInUser, status);
    }
}
