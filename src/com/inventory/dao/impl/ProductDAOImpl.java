package com.inventory.dao.impl;

//import com.inventory.db.DBConnectionManager;
import com.inventory.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.dao.ProductDAO;

public class ProductDAOImpl implements ProductDAO {

    private Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO Product (Name, Description, Quantity, Price, Category, SupplierID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getCategory());
            stmt.setInt(6, product.getSupplierID());
            stmt.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding product.");
        }
    }

    @Override
    public Product getById(int productId) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("Category"),
                        rs.getInt("SupplierID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("Category"),
                        rs.getInt("SupplierID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Product SET Name = ?, Description = ?, Quantity = ?, Price = ?, Category = ?, SupplierID = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getCategory());
            stmt.setInt(6, product.getSupplierID());
            stmt.setInt(7, product.getProductID());
            stmt.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating product.");
        }
    }

    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product.");
        }
    }
}
