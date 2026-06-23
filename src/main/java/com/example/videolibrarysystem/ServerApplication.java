package com.example.videolibrarysystem;
// ServerApplication.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApplication {
    public static void main(String[] args) {
        try {
            // Create RMI Registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Instantiate the service implementation
            DatabaseService service = new DatabaseServiceImpl();
            
            // Bind the remote object stub in the registry
            registry.rebind("DBService", service);
            
            System.out.println(">>> RMI Database Server is running and ready. <<<");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}