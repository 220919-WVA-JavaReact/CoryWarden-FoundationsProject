package com.revature.models;

import java.util.Objects;

public class User {
    //establish variables for user information
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String username;
    private String pw;
    private String role;

    //establish constructors
    public User(int id, String fName, String lName, String email, String username, String pw, String role) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.pw = pw;
        this.role = role;
    }

    public User(int id, String fName, String lName, String email, String username, String role) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public User(String fName, String lName, String email, String username, String pw) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.pw = pw;
    }

    public User(int id, String fName, String lName, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User() {
    }

    //Getter and Setter method to maintain private variables


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", pw='" + pw + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && fName.equals(user.fName) && lName.equals(user.lName) && email.equals(user.email) && username.equals(user.username) && pw.equals(user.pw) && role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, email, username, pw, role);
    }
}
