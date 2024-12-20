package com.inventory.views.frames;

import com.inventory.dao.impl.OrderDAOImpl;
import com.inventory.dao.impl.ProductDAOImpl;
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

public class OrderProductManagementGUI {

    private JFrame frame;
    private JTable orderTable, productTable;
    private JTextField txtOrderID, txtCustomerID, txtOrderDate, txtTotalAmount, txtProductID, txtQuantity;
    private JButton btnAddOrder, btnEditOrder, btnUpdateOrder, btnDeleteOrder, btnClearOrder, btnAddProduct;
    private OrderDAOImpl orderDAO;
    private ProductDAOImpl productDAO;
    private DefaultTableModel orderTableModel, productTableModel;

    public OrderProductManagementGUI(Connection connection) {
        orderDAO = new OrderDAOImpl(connection);
        productDAO = new ProductDAOImpl(connection);
        initialize();
        loadOrderData();
        loadProductData();
    }

    private void initialize() {
        frame = new JFrame("Order and Product Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1000, 700);

        JPanel orderPanel = new JPanel();
        frame.getContentPane().add(orderPanel, BorderLayout.NORTH);

        JLabel lblOrderID = new JLabel("Order ID:");
        txtOrderID = new JTextField(10);
        orderPanel.add(lblOrderID);
        orderPanel.add(txtOrderID);

        JLabel lblCustomerID = new JLabel("Customer ID:");
        txtCustomerID = new JTextField(10);
        orderPanel.add(lblCustomerID);
        orderPanel.add(txtCustomerID);

        JLabel lblOrderDate = new JLabel("Order Date:");
        txtOrderDate = new JTextField(10);
        orderPanel.add(lblOrderDate);
        orderPanel.add(txtOrderDate);

        JLabel lblTotalAmount = new JLabel("Total Amount:");
        txtTotalAmount = new JTextField(10);
        orderPanel.add(lblTotalAmount);
        orderPanel.add(txtTotalAmount);

        btnAddOrder = new JButton("Add Order");
        btnEditOrder = new JButton("Edit Order");
        btnUpdateOrder = new JButton("Update Order");
        btnDeleteOrder = new JButton("Delete Order");
        btnClearOrder = new JButton("Clear Order");

        orderPanel.add(btnAddOrder);
        orderPanel.add(btnEditOrder);
        orderPanel.add(btnUpdateOrder);
        orderPanel.add(btnDeleteOrder);
        orderPanel.add(btnClearOrder);

        JScrollPane orderScrollPane = new JScrollPane();
        orderTable = new JTable();
        orderTableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Order ID", "Customer ID", "Order Date", "Total Amount"});
        orderTable.setModel(orderTableModel);
        orderScrollPane.setViewportView(orderTable);
        frame.getContentPane().add(orderScrollPane, BorderLayout.CENTER);

        JPanel productPanel = new JPanel();
        frame.getContentPane().add(productPanel, BorderLayout.SOUTH);

        JLabel lblProductID = new JLabel("Product ID:");
        txtProductID = new JTextField(10);
        productPanel.add(lblProductID);
        productPanel.add(txtProductID);

        JLabel lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField(10);
        productPanel.add(lblQuantity);
        productPanel.add(txtQuantity);

        btnAddProduct = new JButton("Add Product to Order");
        productPanel.add(btnAddProduct);

        JScrollPane productScrollPane = new JScrollPane();
        productTable = new JTable();
        productTableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Product ID", "Name", "Price"});
        productTable.setModel(productTableModel);
        productScrollPane.setViewportView(productTable);
        frame.getContentPane().add(productScrollPane, BorderLayout.EAST);

        btnAddOrder.addActionListener(e -> addOrder());
        btnEditOrder.addActionListener(e -> editOrder());
        btnUpdateOrder.addActionListener(e -> updateOrder());
        btnDeleteOrder.addActionListener(e -> deleteOrder());
        btnClearOrder.addActionListener(e -> clearOrderForm());
        btnAddProduct.addActionListener(e -> addProductToOrder());

        frame.setVisible(true);
    }

    private void loadOrderData() {
        orderTableModel.setRowCount(0);
        List<Order> orders = orderDAO.getAll();
        for (Order order : orders) {
            orderTableModel.addRow(new Object[] {order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount()});
        }
    }

    private void loadProductData() {
        productTableModel.setRowCount(0);
        List<Product> products = productDAO.getAll();
        for (Product product : products) {
            productTableModel.addRow(new Object[] {product.getProductID(), product.getName(), product.getPrice()});
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
            clearOrderForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an order to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtOrderID.setText(orderTableModel.getValueAt(selectedRow, 0).toString());
        txtCustomerID.setText(orderTableModel.getValueAt(selectedRow, 1).toString());
        txtOrderDate.setText(orderTableModel.getValueAt(selectedRow, 2).toString());
        txtTotalAmount.setText(orderTableModel.getValueAt(selectedRow, 3).toString());
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
            clearOrderForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error updating order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an order to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int orderId = (int) orderTableModel.getValueAt(selectedRow, 0);
        orderDAO.delete(orderId);
        loadOrderData();
        clearOrderForm();
    }

    private void clearOrderForm() {
        txtOrderID.setText("");
        txtCustomerID.setText("");
        txtOrderDate.setText("");
        txtTotalAmount.setText("");
    }

    private void addProductToOrder() {
        try {
            int orderId = Integer.parseInt(txtOrderID.getText());
            int productId = Integer.parseInt(txtProductID.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            if (orderId == 0) {
                JOptionPane.showMessageDialog(frame, "Please select or create an order first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            orderDAO.addProductToOrder(orderId, productId, quantity);
            JOptionPane.showMessageDialog(frame, "Product added to order successfully!");
            txtProductID.setText("");
            txtQuantity.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding product to order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Connection connection = DBConnectionManager.getConnection();
        new OrderProductManagementGUI(connection);
    }
}
