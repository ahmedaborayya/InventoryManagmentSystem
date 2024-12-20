package com.inventory.test;

import com.inventory.dao.impl.CustomerDAOImpl;
import com.inventory.models.Customer;
import com.inventory.db.DBConnectionManager;
import java.sql.Connection;

import java.util.List;
import com.inventory.dao.CustomerDAO;

/**
 *
 * @author ahmed
 */
public class TestCustomerDAO {

    public static void main(String[] args) {
        Connection connection = DBConnectionManager.getConnection();
        CustomerDAO customerDAO = new CustomerDAOImpl(connection);

        // Test adding a new customer
        System.out.println("Adding a new customer...");
        Customer newCustomer = new Customer(0, "Ahmed Gomaa", "El Tahrer", "Cairo", "Egypt","25866", "555-7890");
        customerDAO.add(newCustomer);
        System.out.println("Customer added successfully!");

        // Test retrieving a customer by ID
        System.out.println("\nRetrieving customer with ID 1...");
        Customer customer = customerDAO.getById(1);
        if (customer != null) {
            System.out.println("Customer Details: " + customer);
        } else {
            System.out.println("Customer not found.");
        }

        // Test retrieving all customers
        System.out.println("\nRetrieving all customers...");
        List<Customer> customers = customerDAO.getAll();
        for (Customer c : customers) {
            System.out.println(c);
        }

        // Test updating a customer
        System.out.println("\nUpdating customer with ID 1...");
        Customer updatedCustomer = new Customer(0, "Ahmed Gomaa", "El Tahrer", "Cairo", "Egypt", "111111", "555-7890");
        customerDAO.update(updatedCustomer);
        System.out.println("Customer updated successfully!");

        // Test deleting a customer
        System.out.println("\nDeleting customer with ID 2...");
        customerDAO.delete(9);
        System.out.println("Customer deleted successfully!");

        // Final check of all customers
        System.out.println("\nFinal list of customers...");
        customers = customerDAO.getAll();
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
