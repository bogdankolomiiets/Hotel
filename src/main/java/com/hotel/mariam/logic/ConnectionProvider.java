package com.hotel.mariam.logic;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            Properties connProperties = new Properties();
            //load properties from conn.properties
            try {
                connProperties.load(getClass().getClassLoader().getResourceAsStream("conn.properties"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //getting connection
            try {
                Class.forName(connProperties.getProperty("DRIVER"));
                connection = DriverManager.getConnection(connProperties.getProperty("URL"),
                        connProperties.getProperty("USER"),
                        connProperties.getProperty("PASS"));
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
