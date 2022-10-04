package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReimbursementDAOCSV implements ReimbursementDAO {

    String line = "";
    String splitBy = ",";
    @Override
    public Reimbursement getByReimbursementAuth(int authId) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/reimbursements.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                int csvId = Integer.parseInt(info[3]);
                if (authId == csvId) {
                    //parse info[0] into integer with method below
                    return new Reimbursement(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Float.parseFloat(info[4]), info[5]);
                    //System.out.println("user info from authid");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement getByTicketId(int id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/reimbursements.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                int ticketId = Integer.parseInt(info[0]);
                if (id == ticketId) {
                    //parse info[0] into integer with method below
                    return new Reimbursement(ticketId, info[1], info[2], Integer.parseInt(info[3]), Float.parseFloat(info[4]), info[5]);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement addReimbursement(String username, int authId, float amount, String description) {
        System.out.println("Add ticket functionality. Calling DAO");
        return null;
    }

    @Override
    public Reimbursement getByReimbursementStatus(String status) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/reimbursements.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                if (info[1].equals(status)) {
                    //parse info[0] into integer with method below
                        return new Reimbursement(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Float.parseFloat(info[4]), info[5]);
                    //System.out.println("User info from status");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeStatus(Reimbursement reimbursement) {
        //only available for manager. Option when viewing specific ticket id.
        System.out.println("Called reimbursement update method");
        return false;
    }


}
