package com.inventory.designpatterns.factory;

import com.inventory.models.Product;
import com.inventory.models.ProductCategory.ElectronicsProduct;
import com.inventory.models.ProductCategory.FurnitureProduct;
import com.inventory.models.ProductCategory.GroceriesProduct;

/**
 *
 * @author ahmed
 */
public class ProductFactory {

    public static Product createProduct(String category, int productID, String name, String description, int quantity, double price, int supplierID) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }

        switch (category.toLowerCase()) {
            case "electronics":
                return new ElectronicsProduct.ElectronicsBuilder()
                        .setProductID(productID)
                        .setName(name)
                        .setDescription(description)
                        .setQuantity(quantity)
                        .setPrice(price)
                        .setSupplierID(supplierID)
                        .build();
            case "furniture":
                return new FurnitureProduct.FurnitureBuilder()
                        .setProductID(productID)
                        .setName(name)
                        .setDescription(description)
                        .setQuantity(quantity)
                        .setPrice(price)
                        .setSupplierID(supplierID)
                        .build();
            case "groceries":
                return new GroceriesProduct.GroceriesBuilder()
                        .setProductID(productID)
                        .setName(name)
                        .setDescription(description)
                        .setQuantity(quantity)
                        .setPrice(price)
                        .setSupplierID(supplierID)
                        .build();
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
