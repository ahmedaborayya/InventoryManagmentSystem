package com.inventory.dao.impl;

import com.inventory.models.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.inventory.dao.SupplierDAO;

public class SupplierDAOImpl implements SupplierDAO {
    private Connection connection;

    public SupplierDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Supplier supplier) {
        String sql = "INSERT INTO Supplier (SupplierName, ContactPerson, Email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getSupplierName());
            stmt.setString(2, supplier.getContactPerson());
            stmt.setString(3, supplier.getEmail());
            stmt.executeUpdate();
            System.out.println("Supplier added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding supplier.");
        }
    }

    @Override
    public Supplier getById(int supplierId) {
        String sql = "SELECT * FROM Supplier WHERE SupplierID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(
                    rs.getInt("SupplierID"),
                    rs.getString("SupplierName"),
                    rs.getString("ContactPerson"),
                    rs.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getInt("SupplierID"),
                    rs.getString("SupplierName"),
                    rs.getString("ContactPerson"),
                    rs.getString("Email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE Supplier SET SupplierName = ?, ContactPerson = ?, Email = ? WHERE SupplierID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getSupplierName());
            stmt.setString(2, supplier.getContactPerson());
            stmt.setString(3, supplier.getEmail());
            stmt.setInt(4, supplier.getSupplierId());
            stmt.executeUpdate();
            System.out.println("Supplier updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating supplier.");
        }
    }

    @Override
    public void delete(int supplierId) {
        String sql = "DELETE FROM Supplier WHERE SupplierID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supplierId);
            stmt.executeUpdate();
            System.out.println("Supplier deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting supplier.");
        }
    }
}
