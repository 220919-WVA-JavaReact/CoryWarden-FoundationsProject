package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {

    //grab individual user
    User getByUsername(String username);

    //add new user into database
    User addUser(String fName, String lName, String email, String username, String pw);
    //find login info from database

}
