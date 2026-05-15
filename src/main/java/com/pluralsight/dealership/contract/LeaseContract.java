package com.pluralsight.dealership.contract;

import com.pluralsight.dealership.dealership.Vehicle;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlypayment;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle soldVehicle,
                         double expectedEndingValue, double leaseFee, double monthlypayment) {
        super(date, customerName, customerEmail, soldVehicle);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.monthlypayment = monthlypayment;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getMonthlypayment() {
        return monthlypayment;
    }

    public void setMonthlypayment(double monthlypayment) {
        this.monthlypayment = monthlypayment;
    }

    @Override
    public double getTotalPrice() {
        return (getSoldVehicle().getPrice() - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 36;
        double interestRate = 4.0 / 1200;
        double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
        monthlyPayment = Math.round(monthlyPayment * 100);
        monthlyPayment /= 100;
        return monthlyPayment;
    }
}
