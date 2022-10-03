package com.revature.components;

//Setups up User for ability to log in.
public class Users {
    int id;
    String fName;
    String lName;
    String email;
    String username;
    String pw;
    String role;

    //methods for main below.
    //Call all self variables
    public Users(String fName, String lName, String email, String username, String pw) {
        this.id = 1;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.pw = pw;
        this.role = "Employee";
    }

    //method to authorize userID
    public int getID() {
        return id;
    }

    //setID until database is available
    public void setID(int i) {
        this.id = i;
    }

    //setRole until database is avail
    public void setRole(String i) {
        this.role = i;
    }


}
