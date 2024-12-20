/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventory.dao;

import com.inventory.models.Order;
import com.inventory.models.Product;
import java.util.List;
/**
 *
 * @author ahmed
 */


public interface OrderDAO extends CRUDOperations<Order> {
    // New method to add products to an order
    void addProductToOrder(int orderId, int productId, int quantity);
    
    // New method to get products for an order (for calculating total)
    List<Product> getProductsByOrder(int orderId);
}