package com.inventory.services;

import java.util.List;

public interface CRUDOperationsService<T> {
    void add(T entity);
    T getById(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}
