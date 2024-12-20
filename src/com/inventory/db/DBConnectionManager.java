package com.inventory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singletone(Lazy)
public class DBConnectionManager {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=InventoryManagement;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";
    //jdbc:sqlserver://localhost:1433;databaseName=InventoryManagement;encrypt=true;trustServerCertificate=true;
    //Singletone step 1: private static instance
    private static Connection connection;

    //Singletone step 2: private constractor
    private DBConnectionManager() { }

    //Singletone step 3 public function to get the enstance
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Connected to MS SQL Server successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to the database.");
            }
        }
        return connection;
    }
}
