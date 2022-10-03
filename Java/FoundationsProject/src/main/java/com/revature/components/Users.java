package com.revature.components;

//Setups up User for ability to log in.
public class Users {
    private int id;
    private String fName;
    private String lName;
    private String email;
    private String username;
    private String pw;
    private String role;

    //methods for main below.
    //Call all self variables
    public Users(String fName, String lName, String email, String username, String pw) {
        super();
        this.id = 1;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.pw = pw;
        this.role = "Manager";
    }

    //This prevents arbitrary external interference, which could bring the object into an invalid or inconsistent state.
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getfName() { return fName; }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() { return lName; }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() { return pw; }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }



}
