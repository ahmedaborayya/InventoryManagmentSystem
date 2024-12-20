package com.inventory.services.impl;

import com.inventory.models.Order;
import com.inventory.dao.OrderDAO;
import com.inventory.dao.ProductDAO;
import com.inventory.models.Product;
import com.inventory.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;

    public OrderServiceImpl(OrderDAO orderCRUD, ProductDAO productDAO) {
        this.orderDAO = orderCRUD;
        this.productDAO = productDAO;
    }

    @Override
    public void add(Order order, List<Integer> productIds) {
        // 1. Validate order
        if (order == null || order.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Invalid order or customer ID.");
        }

        // 2. Create order in database
        orderDAO.add(order);

        // 3. For each product, associate it with the order in Order_Product table
        for (Integer productId : productIds) {
            Product product = productDAO.getById(productId); // Fetch product by ID

            if (product == null) {
                throw new IllegalArgumentException("Invalid product ID: " + productId);
            }

            // Add to Order_Product table
            orderDAO.addProductToOrder(order.getOrderId(), productId, 1); // Assuming quantity is 1 for simplicity
        }
    }

//    @Override
//    public void add(Order order) {
//        if (order == null || order.getCustomerId() <= 0 || order.getOrderDate() == null) {
//            throw new IllegalArgumentException("Invalid order data.");
//        }
//        orderDAO.add(order);
//    }
    @Override
    public Order getById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid order ID.");
        }
        return orderDAO.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public void update(Order order) {
        if (order == null || order.getOrderId() <= 0) {
            throw new IllegalArgumentException("Invalid order data.");
        }
        orderDAO.update(order);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid order ID.");
        }
        try {
            orderDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete order. Ensure there are no dependent records.", e);
        }
    }
}
