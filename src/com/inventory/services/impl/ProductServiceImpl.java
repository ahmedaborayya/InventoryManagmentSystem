package com.inventory.services.impl;

import com.inventory.models.Product;
import com.inventory.dao.ProductDAO;
import com.inventory.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void add(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        productDAO.add(product);
    }

    @Override
    public Product getById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid product ID.");
        }
        return productDAO.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public void update(Product product) {
        if (product == null || product.getProductID() <= 0) {
            throw new IllegalArgumentException("Invalid product data.");
        }
        productDAO.update(product);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid product ID.");
        }
        productDAO.delete(id);
    }
}
