package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReimbursementDAOCSV implements ReimbursementDAO {

    String line = "";
    String splitBy = ",";
    @Override
    public Reimbursement getByReimbursementAuth(int id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/reimbursements.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                int authId = Integer.parseInt(info[3]);
                if (authId == id) {
                    //parse info[0] into integer with method below
                    return new Reimbursement(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Float.parseFloat(info[4]), info[5]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeStatus(String choice, int id, String status) {
        return false;
    }

}
