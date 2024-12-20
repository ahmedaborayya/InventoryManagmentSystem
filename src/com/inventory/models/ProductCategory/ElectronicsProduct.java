package com.inventory.models.ProductCategory;

import com.inventory.models.Product;

public class ElectronicsProduct extends Product {

    public ElectronicsProduct(int productID, String name, String description, int quantity, double price, int supplierID) {
        super(productID, name, description, quantity, price, "Electronics", supplierID);
    }

    // Static Builder class
    public static class ElectronicsBuilder {
        private int productID;
        private String name;
        private String description;
        private int quantity;
        private double price;
        private int supplierID;

        public ElectronicsBuilder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public ElectronicsBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ElectronicsBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ElectronicsBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ElectronicsBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ElectronicsBuilder setSupplierID(int supplierID) {
            this.supplierID = supplierID;
            return this;
        }

        public ElectronicsProduct build() {
            return new ElectronicsProduct(productID, name, description, quantity, price, supplierID);
        }
    }
}
