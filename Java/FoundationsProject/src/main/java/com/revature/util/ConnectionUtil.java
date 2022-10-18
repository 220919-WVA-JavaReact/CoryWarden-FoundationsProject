package com.revature.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Making singleton connection instance
public class ConnectionUtil {
    //Private static instance
    private static Connection conn = null;
    //private constructor
    private ConnectionUtil() {
    }

    //public static getInstance method
    public static Connection getConn()  {
        try {
            //check for existing connection
            if (conn != null && !conn.isClosed()) {
                //System.out.println("Use a previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            //print stacktrace
            System.out.println("Could not establish connection.");
            return null;
        }

        //Storing info in environment variables made in run environment (TestConnection atm)
//        String url = System.getenv("url");
//        String username = System.getenv("username");
//        String password = System.getenv("password");

        String url;
        String username;
        String password;
        Properties prop = new Properties();

        try {
            //connect to database successfully
            //prop.load(new FileReader("src/main/resources/application.properties"));
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            Class.forName("org.postgresql.Driver");
           conn = DriverManager.getConnection(url, username, password);
       } catch (SQLException e) {
            //print exception if database connection fails
          System.out.println("Could not establish connection.");
          e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
