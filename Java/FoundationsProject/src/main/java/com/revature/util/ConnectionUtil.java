package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Making singleton connection instance
public class ConnectionUtil {
    //Private static instance
    private static Connection conn = null;

    //private constructor
    private ConnectionUtil() {
    }

    //public static getInstance method
    public static Connection getConn() {
        try {
            //check for existing connection
            if (conn != null && !conn.isClosed()) {
                //System.out.println("Use a previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            //print stacktrace
            e.printStackTrace();
            return null;
        }

        //Storing info in environment variables made in run environment (TestConnection atm)
        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");

        try {
            //connect to database successfully
           conn = DriverManager.getConnection(url, username, password);
           //System.out.println("Connection established"); -- checking that db conn was established.
       } catch (SQLException e) {
            //print exception if database connection fails
          System.out.println("Couldn't establish connection");
          e.printStackTrace();
        }

        return conn;
    }


}
