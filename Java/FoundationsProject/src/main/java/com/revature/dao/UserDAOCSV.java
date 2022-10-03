package com.revature.dao;

import com.revature.models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserDAOCSV implements UserDAO {

    String line = "";
    String splitBy = ",";

    @Override
    public User getByUsername(String username) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/users.csv"));

            //allowing us to read through file until ended
            while ((line = br.readLine()) != null) {

                //This is where we'll do our logic to split it. Check each line to see if user matches input user
                //Should be able to send user back to client side.
                String[] info = line.split(splitBy);
                if (info[4].equals(username)) {
                    //parse info[0] into integer with method below
                    return new User(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], info[5], info[6]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
