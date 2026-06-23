package com.example.videolibrarysystem;// DatabaseServiceImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl extends UnicastRemoteObject implements DatabaseService {
    
    protected DatabaseServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> getData() throws RemoteException {
        List<String> results = new ArrayList<>();
        String query = "SELECT name_column FROM your_table_name"; // Replace with your table info
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                results.add(rs.getString("name_column"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database error occurred while fetching data.", e);
        }
        return results;
    }

    @Override
    public boolean insertData(String data) throws RemoteException {
        String query = "INSERT INTO your_table_name (name_column) VALUES (?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, data);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database error occurred while inserting data.", e);
        }
    }
}