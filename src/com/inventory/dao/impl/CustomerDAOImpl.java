package com.inventory.dao.impl;

import com.inventory.db.DBConnectionManager;
import com.inventory.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.dao.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection connection;

    public CustomerDAOImpl(Connection connection) {
    this.connection = connection;    
    //connection = DBConnectionManager.getConnection();
    }

    @Override
    public void add(Customer customer) {
        String sql = "INSERT INTO Customer (Name, Street, City, State, ZipCode, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getStreet());
            stmt.setString(3, customer.getCity());
            stmt.setString(4, customer.getState());
            stmt.setString(5, customer.getZipCode());
            stmt.setString(6, customer.getPhoneNumber());
            stmt.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding customer.");
        }
    }

    @Override
    public Customer getById(int customerId) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Street"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("ZipCode"),
                        rs.getString("PhoneNumber")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving customer.");
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Street"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getString("ZipCode"),
                        rs.getString("PhoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all customers.");
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE Customer SET Name = ?, Street = ?, City = ?, State = ?, ZipCode = ?, PhoneNumber = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getStreet());
            stmt.setString(3, customer.getCity());
            stmt.setString(4, customer.getState());
            stmt.setString(5, customer.getZipCode());
            stmt.setString(6, customer.getPhoneNumber());
            stmt.setInt(7, customer.getCustomerId());
            stmt.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating customer.");
        }
    }

    @Override
    public void delete(int customerId) {
        try {
            // First, check if there are any orders associated with the customer
            String checkOrdersSql = "SELECT COUNT(*) FROM [Order] WHERE CustomerID = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkOrdersSql)) {
                checkStmt.setInt(1, customerId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Cannot delete customer. There are associated orders.");
                    return; // Prevent deletion if orders exist
                }
            }

            // If no orders, proceed with deletion
            String sql = "DELETE FROM Customer WHERE CustomerID = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, customerId);
                stmt.executeUpdate();
                System.out.println("Customer deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting customer.");
        }
    }

}
