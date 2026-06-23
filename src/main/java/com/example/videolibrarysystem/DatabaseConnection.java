package com.example.videolibrarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Define configuration settings as constants for easier maintenance
    private static final String URL = "jdbc:mysql://localhost:3307/rentalsystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Consider moving sensitive credentials to an environment variable or properties file later

    public static Connection getConnection() throws SQLException {
        try {
            // Updated class name for modern MySQL Connector/J (8.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found in classpath", e);
        }
            
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}