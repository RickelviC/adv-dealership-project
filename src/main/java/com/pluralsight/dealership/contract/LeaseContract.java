package com.pluralsight.dealership.contract;

import com.pluralsight.dealership.dealership.Vehicle;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPaymentForLease;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle soldVehicle,
                         double expectedEndingValue, double leaseFee, double monthlyPaymentForLease) {
        super(date, customerName, customerEmail, soldVehicle);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.monthlyPaymentForLease = monthlyPaymentForLease;
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

    public double getMonthlyPaymentForLease() {
        return monthlyPaymentForLease;
    }

    public void setMonthlyPaymentForLease(double monthlyPaymentForLease) {
        this.monthlyPaymentForLease = monthlyPaymentForLease;
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
