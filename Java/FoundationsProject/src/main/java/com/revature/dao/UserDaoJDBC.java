package com.revature.dao;

import com.revature.Main;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements UserDAO {
    @Override
    public User getByUsername(String username) {
        User u = new User();
        try (Connection conn = ConnectionUtil.getConn()){
            String sql = "SELECT * FROM users WHERE username = ?";
            //Put ? wherever information will receive input
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Answer ? below with parameter and variable/input
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            //to store catch result, use if statement
            if (rs != null) {
                //move cursor forward to pull info out & store in obj.
                rs.next();
                int id = rs.getInt("id");
                String first = rs.getString("fname");
                String last = rs.getString("lname");
                String email = rs.getString("email");
                username = rs.getString("username");
                String pw = rs.getString("pw");
                String role = rs.getString("role");

                //Create obj to return at the end of method.
                u = new User(id, first, last, email, username, pw, role);
            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch user.");
        }
        return u;
    }

    @Override
    public User addUser(User u) {
        try (Connection conn = ConnectionUtil.getConn()) {
            String sql = "INSERT INTO users (fName,lName,email,username,pw) VALUES (?,?,?,?,?) RETURNING *";
            //Put ? wherever information will receive input
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //Answer ? below with parameter and variable/input
            pstmt.setString(1, u.getfName());
            pstmt.setString(2, u.getlName());
            pstmt.setString(3, u.getEmail());
            pstmt.setString(4, u.getUsername());
            pstmt.setString(5, u.getPw());
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                rs.next();
                int id = rs.getInt("id");
                String first = rs.getString("fname");
                String last = rs.getString("lname");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String pw = rs.getString("pw");
                String role = rs.getString("role");

                u = new User(id, first, last, email, username, pw, role);
                System.out.println("Success! You are now able to login.");
                //System.out.println("addUser sysout: " + u); --Checking where user is losing role
            }
        } catch (SQLException e) {
            System.out.println("Unable to add user. Username or email is already in use.");
        }
        return u;
    }

        @Override
    public List<User> getAllUsers() {
        //SQL query to get all users back.

        List<User> users = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConn()){
            assert conn != null;
            Statement stmt = conn.createStatement();
            String sql = "SELECT u.id, u.fname, u.lname, u.email, u.username, u.\"role\"  FROM users u ORDER BY id";
            ResultSet rs = stmt.executeQuery(sql);

            //to store all, use while loop
            while (rs.next()) {
                int id = rs.getInt("id");
                String first = rs.getString("fname");
                String last = rs.getString("lname");
                String email = rs.getString("email");
                String username = rs.getString("username");
                //String pw = rs.getString("pw");
                String role = rs.getString("role");

                User u = new User(id, first, last, email, username, role);
                users.add(u);

            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch all Users.");
        }
        return users;
    }

    @Override
    public User updateRole(User u, int newRole) {
        try (Connection conn = ConnectionUtil.getConn()) {
            String sql = "UPDATE users SET role = ? WHERE username = ? RETURNING *";
            assert conn != null;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setString(1, "manager");
            pstmt.setString(2, u.getUsername());

            if (newRole == 1) {
                //if user inputs 1, make chosen user a manager
                pstmt.setString(1, "manager");
            } else {
                //if user inputs anything else, make chosen user an employee.
                pstmt.setString(1, "employee");
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                rs.next();
                int id = rs.getInt("id");
                String first = rs.getString("fname");
                String last = rs.getString("lname");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String pw = rs.getString("pw");
                String role = rs.getString("role");

                u = new User(id, first, last, email, username, pw, role);
                System.out.println(username + " has now been updated to: " + role);
            }
        } catch (SQLException e) {
            System.out.println("This ticket was not updated.");
        }
        return u;
    }
}
