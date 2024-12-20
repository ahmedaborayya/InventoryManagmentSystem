package com.inventory.dao.impl;

import com.inventory.models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.dao.OrderDAO;
import com.inventory.models.Product;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void updateOrderTotalAmount(int orderId) {
        String sql = "UPDATE [Order] SET TotalAmount = (SELECT SUM(p.Price * op.Quantity) "
                + "FROM Order_Product op "
                + "JOIN Product p ON op.ProductID = p.ProductID "
                + "WHERE op.OrderID = ?) "
                + "WHERE OrderID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating total amount for order ID: " + orderId);
        }
    }

    @Override
    public void addProductToOrder(int orderId, int productId, int quantity) {
        String sql = "INSERT INTO Order_Product (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();

            // Update the total amount for the order
            updateOrderTotalAmount(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error associating product with order.");
        }
    }

    @Override
    public List<Product> getProductsByOrder(int orderId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.Name, p.Price, op.Quantity "
                + "FROM Product p "
                + "INNER JOIN Order_Product op ON p.ProductID = op.ProductID "
                + "WHERE op.OrderID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductID(rs.getInt("ProductID"));
                    product.setName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setQuantity(rs.getInt("Quantity")); // Set the quantity from the Order_Product table
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving products for order.");
        }
        return products;
    }

    public double getTotalPriceByOrder(int orderId) {
        double totalPrice = 0.0;
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.Name, p.Price, op.Quantity "
                + "FROM Product p "
                + "INNER JOIN Order_Product op ON p.ProductID = op.ProductID "
                + "WHERE op.OrderID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductID(rs.getInt("ProductID"));
                    product.setName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setQuantity(rs.getInt("Quantity"));

                    // Calculate total price for this product
                    double productTotalPrice = product.getPrice() * product.getQuantity();
                    totalPrice += productTotalPrice;  // Add it to the total price

                    products.add(product);  // Optionally, you can still add products to the list if needed
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving products for order.");
        }

        return totalPrice;
    }

    @Override
    public void add(Order order) {
        String sql = "INSERT INTO [Order] (CustomerID, OrderDate) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, (Date) order.getOrderDate());
            stmt.executeUpdate();
            System.out.println("Order added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding order.");
        }
    }

    @Override
    public Order getById(int orderId) {
        String sql = "SELECT * FROM [Order] WHERE OrderID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("OrderDate"),
                        rs.getDouble("TotalAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM [Order]";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("OrderDate"),
                        rs.getDouble("TotalAmount")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE [Order] SET CustomerID = ?, OrderDate = ?, TotalAmount = ? WHERE OrderID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, (Date) order.getOrderDate());
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setInt(4, order.getOrderId());
            stmt.executeUpdate();
            System.out.println("Order updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating order.");
        }
    }

    @Override
    public void delete(int orderId) {
        String sql = "DELETE FROM [Order] WHERE OrderID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
            System.out.println("Order deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting order.");
        }
    }
}
