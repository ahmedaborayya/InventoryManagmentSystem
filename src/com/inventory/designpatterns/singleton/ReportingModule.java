package com.inventory.designpatterns.singleton;

public class ReportingModule {
    // Static instance variable (the single instance of the class)
    private static ReportingModule instance;

    // Private constructor to prevent external instantiation
    private ReportingModule() {}

    // Public static method to get the single instance
    public static synchronized ReportingModule getInstance() {
        if (instance == null) {
            instance = new ReportingModule();
        }
        return instance;
    }

    // Method to generate sales report
    public void generateSalesReport() {
        System.out.println("Generating Sales Report...");
        // Add logic to generate and display sales report
    }

    // Method to generate inventory status report
    public void generateInventoryStatusReport() {
        System.out.println("Generating Inventory Status Report...");
        // Add logic to generate and display inventory report
    }
}
