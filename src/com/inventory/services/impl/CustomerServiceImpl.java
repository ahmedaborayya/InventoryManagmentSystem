package com.inventory.services.impl;

import com.inventory.models.Customer;
import com.inventory.services.CustomerService;
import java.util.List;
import com.inventory.dao.CustomerDAO;
import java.util.ArrayList;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    // Constructor injection for DAO
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void add(Customer customer) {
        // Business logic (e.g., validation, transformations)
        if (customer == null || customer.getName() == null || customer.getName().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        customerDAO.add(customer);
    }

    @Override
    public Customer getById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid customer ID.");
        }
        return customerDAO.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerDAO.getAll();
        return customers != null ? customers : new ArrayList<>();
    }

    @Override
    public void update(Customer customer) {
        if (customer == null || customer.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Invalid customer data.");
        }
        customerDAO.update(customer);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid customer ID.");
        }
        try {
            customerDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete customer. Ensure there are no dependent records.", e);
        }
    }
}
