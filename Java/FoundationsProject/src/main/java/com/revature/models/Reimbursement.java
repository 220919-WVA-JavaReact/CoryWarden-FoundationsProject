package com.revature.models;

import java.util.Objects;

public class Reimbursement {
    private int id;
    private String status;
    private int authId;
    private String username;
    private float amount;
    private String description;
    //Constructors

    public Reimbursement(int id, String status, int authId, String username, float amount, String description) {
        this.id = id;
        this.status = status;
        this.username = username;
        this.authId = authId;
        this.amount = amount;
        this.description = description;
    }

    public Reimbursement(int authId, String username, float amount, String description) {
        this.username = username;
        this.authId = authId;
        this.amount = amount;
        this.description = description;
    }

    public Reimbursement() {
    }

    //Getter and Setter methods to maintain private variables


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

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", authId=" + authId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && authId == that.authId && Float.compare(that.amount, amount) == 0 && status.equals(that.status) && username.equals(that.username) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, username, authId, amount, description);
    }
}
