package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import java.sql.SQLException;

public class UserService {

    UserDAO ud = new UserDaoJDBC();

    public User login(User loggedInUser) throws SQLException {
        //validate user authorization with username and pw
        return ud.getAuth(loggedInUser.getUsername(), loggedInUser.getPw());
    }

    public User register (User newUser) {
        //add user to the database and return new user.
        return ud.addUser(newUser);
    }

    public User promoteUser(User loggedInUser, String status) {
        return ud.updateRole(loggedInUser, status);
    }
}
