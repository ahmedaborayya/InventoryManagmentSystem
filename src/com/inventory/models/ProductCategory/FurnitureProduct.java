package com.inventory.models.ProductCategory;

import com.inventory.models.Product;

public class FurnitureProduct extends Product {

    public FurnitureProduct(int productID, String name, String description, int quantity, double price, int supplierID) {
        super(productID, name, description, quantity, price, "Furniture", supplierID);
    }

    // Static Builder class
    public static class FurnitureBuilder {

        private int productID;
        private String name;
        private String description;
        private int quantity;
        private double price;
        private int supplierID;

        public FurnitureBuilder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public FurnitureBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FurnitureBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public FurnitureBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public FurnitureBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public FurnitureBuilder setSupplierID(int supplierID) {
            this.supplierID = supplierID;
            return this;
        }

        public FurnitureProduct build() {
            return new FurnitureProduct(productID, name, description, quantity, price, supplierID);
        }
    }
}
