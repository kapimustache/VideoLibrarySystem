package com.example.videolibrarysystem;

//DatabaseService.java
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DatabaseService extends Remote {
 // Example method to fetch data
 List<String> getData() throws RemoteException;
 
 // Example method to insert data
 boolean insertData(String data) throws RemoteException;
}