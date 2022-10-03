package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOCSV;
import com.revature.models.User;

public class UserService {

    UserDAO ud = new UserDAOCSV();

    public void login(String username, String password) {
        //Get the account associated with username and verify the password to log the user in.
        /*
        1) Call Database and find info based off submitted username
        2) Check returned pw of username in database against the input to verify they are equal
         */
        User user = ud.getByUsername(username);
        if (user.getPw().equals(password)) {
            System.out.println("You are an existing user.");
            System.out.println(user);
        } else {
            System.out.println("You do not have an account in our database.");
        }
    }
}
