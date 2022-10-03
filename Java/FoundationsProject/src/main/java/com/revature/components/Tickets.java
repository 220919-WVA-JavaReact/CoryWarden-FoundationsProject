package com.revature.components;

//Ticket setup for reimbursement form.

public class Tickets {
    int id;
    String status;
    String username;
    int authId;
    float amount;
    String description;

    //methods for main below.
    //Call all self variables
    public Tickets(String username, int authId, float amount, String description) {
        this.id = id;
        this.status = "Pending";
        this.username = username;
        this.authId = authId;
        this.amount = amount;
        this.description = description;
    }

    //method to get ticket id.
    public int getID() {
        return id;
    }

}
