package com.pluralsight.dealership.contract;

import com.pluralsight.dealership.dealership.Vehicle;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle soldVehicle;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle soldVehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.soldVehicle = soldVehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getSoldVehicle() {
        return soldVehicle;
    }

    public void setSoldVehicle(Vehicle soldVehicle) {
        this.soldVehicle = soldVehicle;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

}
