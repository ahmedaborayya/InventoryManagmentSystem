package com.inventory.views.frames;

import com.inventory.dao.impl.OrderDAOImpl;
import com.inventory.db.DBConnectionManager;
import com.inventory.models.Order;
import com.inventory.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class OrderManagementGUI {

    private JFrame frame;
    private JTable table;
    private JTextField txtOrderID, txtCustomerID, txtOrderDate, txtTotalAmount;
    private JButton btnAdd, btnEdit, btnUpdate, btnDelete, btnClear;
    private OrderDAOImpl orderDAO;
    private DefaultTableModel tableModel;

    public OrderManagementGUI(Connection connection) {
        orderDAO = new OrderDAOImpl(connection);
        initialize();
        loadOrderData();
    }

    private void initialize() {
        frame = new JFrame("Order Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 600);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblOrderID = new JLabel("Order ID:");
        txtOrderID = new JTextField(10);
        panel.add(lblOrderID);
        panel.add(txtOrderID);

        JLabel lblCustomerID = new JLabel("Customer ID:");
        txtCustomerID = new JTextField(10);
        panel.add(lblCustomerID);
        panel.add(txtCustomerID);

        JLabel lblOrderDate = new JLabel("Order Date:");
        txtOrderDate = new JTextField(10);
        panel.add(lblOrderDate);
        panel.add(txtOrderDate);

        JLabel lblTotalAmount = new JLabel("Total Amount:");
        txtTotalAmount = new JTextField(10);
        panel.add(lblTotalAmount);
        panel.add(txtTotalAmount);

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        panel.add(btnAdd);
        panel.add(btnEdit);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Order ID", "Customer ID", "Order Date", "Total Amount"});
        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editOrder();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        frame.setVisible(true);
    }

    private void loadOrderData() {
        tableModel.setRowCount(0);
        List<Order> orders = orderDAO.getAll();
        for (Order order : orders) {
            tableModel.addRow(new Object[] {order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount()});
        }
    }

    private void addOrder() {
        try {
            Order order = new Order();
            order.setCustomerId(Integer.parseInt(txtCustomerID.getText()));
            order.setOrderDate(java.sql.Date.valueOf(txtOrderDate.getText()));
            order.setTotalAmount(Double.parseDouble(txtTotalAmount.getText()));
            orderDAO.add(order);
            loadOrderData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editOrder() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an order to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtOrderID.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtCustomerID.setText(tableModel.getValueAt(selectedRow, 1).toString());
        txtOrderDate.setText(tableModel.getValueAt(selectedRow, 2).toString());
        txtTotalAmount.setText(tableModel.getValueAt(selectedRow, 3).toString());
    }

    private void updateOrder() {
        try {
            Order order = new Order();
            order.setOrderId(Integer.parseInt(txtOrderID.getText()));
            order.setCustomerId(Integer.parseInt(txtCustomerID.getText()));
            order.setOrderDate(java.sql.Date.valueOf(txtOrderDate.getText()));
            order.setTotalAmount(Double.parseDouble(txtTotalAmount.getText()));
            orderDAO.update(order);
            loadOrderData();
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error updating order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteOrder() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an order to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        orderDAO.delete(orderId);
        loadOrderData();
        clearForm();
    }

    private void clearForm() {
        txtOrderID.setText("");
        txtCustomerID.setText("");
        txtOrderDate.setText("");
        txtTotalAmount.setText("");
    }

    public static void main(String[] args) {
        // Replace with your database connection setup
        Connection connection = DBConnectionManager.getConnection();
        new OrderManagementGUI(connection);
    }
}
