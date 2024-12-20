/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.test;

import com.inventory.designpatterns.singleton.ReportingModule;

/**
 *
 * @author ahmed
 */
public class TestReportingSingleton {
    public static void main(String[] args) {
        ReportingModule report1 = ReportingModule.getInstance();
        ReportingModule report2 = ReportingModule.getInstance();

        System.out.println(report1 == report2); // Should print 'true'
    }
}
