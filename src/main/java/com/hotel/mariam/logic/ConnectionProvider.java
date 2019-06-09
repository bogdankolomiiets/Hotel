package com.hotel.mariam.logic;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private BasicDataSource dataSource = new BasicDataSource();
    private Properties connProperties = new Properties();

    public Connection getConnection() {
        //load properties from conn.properties
        try {
            connProperties.load(ConnectionProvider.class.getClassLoader().getResourceAsStream("conn.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //getting connection
        try {
            Class.forName(connProperties.getProperty("DRIVER"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(connProperties.getProperty("URL"));
        dataSource.setUsername(connProperties.getProperty("USER"));
        dataSource.setPassword(connProperties.getProperty("PASS"));
        dataSource.setMinIdle(Integer.parseInt(connProperties.getProperty("minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(connProperties.getProperty("maxIdle")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(connProperties.getProperty("maxActive")));
        dataSource.setMaxWait(Integer.valueOf(connProperties.getProperty("maxWait")));

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
