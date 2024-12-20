/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventory.services;
import com.inventory.models.Order;
import java.util.List;
/**
 *
 * @author ahmed
 */
public interface OrderService {
    // Add a new order and associate products with it
    void add(Order order, List<Integer> productIds);

    // Get an order by its ID
    Order getById(int id);

    // Get a list of all orders
    List<Order> getAll();

    // Update an existing order
    void update(Order order);

    // Delete an order by its ID
    void delete(int orderId);
}
