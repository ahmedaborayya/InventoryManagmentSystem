/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.models;

/**
 *
 * @author ahmed
 */
public class Customer {

    private int customerId;
    private String name;
    private String Street;
    private String City;
    private String State;
    private String ZipCode;
    private String phoneNumber;

    public Customer(int customerId, String name, String Street, String City, String State, String ZipCode, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.Street = Street;
        this.City = City;
        this.State = State;
        this.ZipCode = ZipCode;
        this.phoneNumber = phoneNumber;
    }

    

    public int getCustomerId() {
        return customerId;
    }

    public void setCustmerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setNama(String name) {
        this.name = name;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", name=" + name + ", Street=" + Street + ", City=" + City + ", State=" + State + ", ZipCode=" + ZipCode + ", phoneNumber=" + phoneNumber + '}';
    }
    
    

}
