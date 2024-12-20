/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.models;

/**
 *
 * @author ahmed
 */
public class Product {

    private int productID;    // Unique identifier for the product
    private String name;      // Name of the product
    private String description; // Description of the product
    private int quantity;     // Quantity available in stock
    private double price;     // Price per unit
    private String category;  // Product category
    private int supplierID;   // Reference to the supplier

    // Constructor
    public Product(int productID, String name, String description, int quantity, double price, String category, int supplierID) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.supplierID = supplierID;
    }

    public Product() {
        
    }

    // Getters and Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productId) {
        this.productID = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierId(int supplierId) {
        this.supplierID = supplierId;
    }
}
