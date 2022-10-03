package com.revature.components;

//Ticket setup for reimbursement form.

public class Tickets {
    private int id;
    private String status;
    private String username;
    private int authId;
    private float amount;
    private String description;

    //methods for main below.
    //Call all self variables
    public Tickets(String username, int authId, float amount, String description) {
        this.id = 1;
        this.status = "Pending";
        this.username = username;
        this.authId = authId;
        this.amount = amount;
        this.description = description;
    }

    //method to get ticket id.
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public int getAuthId() {
        return authId;
    }


    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public float getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

}
