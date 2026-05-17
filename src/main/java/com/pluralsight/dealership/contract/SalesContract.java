package com.pluralsight.dealership.contract;

import com.pluralsight.dealership.dealership.Vehicle;

public class SalesContract extends Contract{
    private double salesTax;
    private double recodingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle soldVehicle, double salesTax,
                         double recodingFee, double processingFee, boolean isFinanced) {
        super(date, customerName, customerEmail, soldVehicle);
        this.salesTax = salesTax;
        this.recodingFee = recodingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecodingFee() {
        return recodingFee;
    }

    public void setRecodingFee(int recodingFee) {
        this.recodingFee = recodingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
    @Override
    public double getTotalPrice() {
        return getSoldVehicle().getPrice() + getSalesTax() + getRecodingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        int numberOfPayments = 0;
        double interestRate = 0;
        if (isFinanced) {
            if (getSoldVehicle().getPrice() >= 10000) {
                numberOfPayments = 48;
                interestRate = 4.25 / 1200;
            } else {
                numberOfPayments = 24;
                interestRate = 5.25 / 1200;
            }

            double monthlyPayment = getTotalPrice() * (interestRate * Math.pow(1 + interestRate, numberOfPayments)) / (Math.pow(1 + interestRate, numberOfPayments) - 1);
            monthlyPayment = Math.round(monthlyPayment * 100);
            monthlyPayment /= 100;
            return monthlyPayment;
        } else {
            return 0.0;
        }
    }
}
