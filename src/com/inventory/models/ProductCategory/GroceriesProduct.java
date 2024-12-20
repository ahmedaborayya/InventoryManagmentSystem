package com.inventory.models.ProductCategory;

import com.inventory.models.Product;

public class GroceriesProduct extends Product {

    public GroceriesProduct(int productID, String name, String description, int quantity, double price, int supplierID) {
        super(productID, name, description, quantity, price, "Groceries", supplierID);
    }

    // Static Builder class
    public static class GroceriesBuilder {

        private int productID;
        private String name;
        private String description;
        private int quantity;
        private double price;
        private int supplierID;

        public GroceriesBuilder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public GroceriesBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GroceriesBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public GroceriesBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public GroceriesBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public GroceriesBuilder setSupplierID(int supplierID) {
            this.supplierID = supplierID;
            return this;
        }

        public GroceriesProduct build() {
            return new GroceriesProduct(productID, name, description, quantity, price, supplierID);
        }
    }
}
