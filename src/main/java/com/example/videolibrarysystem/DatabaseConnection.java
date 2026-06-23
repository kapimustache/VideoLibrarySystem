package com.example.videolibrarysystem;
import java.sql.*;

public class DatabaseConnection {
	
	public Connection getConnection() throws SQLException{
	
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
		}
		
		catch(ClassNotFoundException e) {
			throw new SQLException("MySQL server not found");
		}
			
		 return DriverManager.getConnection("jdbc:mysql://localhost:3307/rentalsystem", "root", "");
			
	}
}
	


