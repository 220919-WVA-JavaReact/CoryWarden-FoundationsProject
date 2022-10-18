package com.revature.dao;

import com.revature.models.User;
import java.util.List;

public interface UserDAO {

    //grab individual user
    User getByUsername(String username);

    //login auth
    User getAuth(String username, String pw);

    //add new user into database;
    User addUser(User u);
    //find login info from database

    List<User> getAllUsers();

    User updateRole(User u, String status);

}
