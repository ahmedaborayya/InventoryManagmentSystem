package com.inventory.services.impl;

import com.inventory.models.Supplier;
import com.inventory.dao.SupplierDAO;
import com.inventory.services.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @Override
    public void add(Supplier supplier) {
        if (supplier == null || supplier.getSupplierName() == null || supplier.getSupplierName().isEmpty()) {
            throw new IllegalArgumentException("Supplier name cannot be null or empty.");
        }
        supplierDAO.add(supplier);
    }

    @Override
    public Supplier getById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid supplier ID.");
        }
        return supplierDAO.getById(id);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierDAO.getAll();
    }

    @Override
    public void update(Supplier supplier) {
        if (supplier == null || supplier.getSupplierId() <= 0) {
            throw new IllegalArgumentException("Invalid supplier data.");
        }
        supplierDAO.update(supplier);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid supplier ID.");
        }
        supplierDAO.delete(id);
    }
}
