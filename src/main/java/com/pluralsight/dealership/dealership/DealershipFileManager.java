package com.pluralsight.dealership.dealership;

import com.pluralsight.dealership.contract.Contract;
import com.pluralsight.dealership.contract.LeaseContract;
import com.pluralsight.dealership.contract.SalesContract;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("dealership.csv"))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (lineNumber == 0) { // dealership info
                    String name = fields[0];
                    String address = fields[1];
                    String phone = fields[2];
                    dealership = new Dealership(name, address, phone);
                } else { // vehicle info
                    int vin = Integer.parseInt(fields[0]);
                    int year = Integer.parseInt(fields[1]);
                    String make = fields[2];
                    String model = fields[3];
                    String vehicleType = fields[4];
                    String color = fields[5];
                    int odometer = Integer.parseInt(fields[6]);
                    double price = Double.parseDouble(fields[7]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    vehicles.add(vehicle);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dealership != null) {
            for (Vehicle vehicle : vehicles) {
                dealership.addVehicle(vehicle);
            }
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dealership.csv"))) {
            // Write dealership information
            bw.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bw.newLine();

            // Write vehicle inventory
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                bw.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel()
                        + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer()
                        + "|" + vehicle.getPrice());
                bw.newLine();
            }

            System.out.println("Dealership saved successfully to dealership.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contract getContract() {
        SalesContract sales = null;
        LeaseContract lease = null;

        try (BufferedReader br = new BufferedReader(new FileReader("Contracts.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] fields = line.split("\\|");
                String first = fields[0];

                if (first.equalsIgnoreCase("sale")) {
                    String contractDate = fields[1];
                    String customerName = fields[2];
                    String customerEmail = fields[3];
                    int carID = Integer.parseInt(fields[4]);
                    int year = Integer.parseInt(fields[5]);
                    String make = fields[6];
                    String model = fields[7];
                    String vehicleType = fields[8];
                    String color = fields[9];
                    int odometer = Integer.parseInt(fields[10]);
                    double price = Double.parseDouble(fields[11]);
                    double salesTax = Double.parseDouble(fields[12]);
                    double recordingFee = Double.parseDouble(fields[13]);
                    double processingFee = Double.parseDouble(fields[14]);
                    double totalCost = Double.parseDouble(fields[15]);
                    boolean finance = fields[16].equalsIgnoreCase("YES");
                    double monthlyPayment = Double.parseDouble(fields[17]);

                    Vehicle vehicle = new Vehicle(carID, year, make, model, vehicleType, color, odometer, price);

                    sales = new SalesContract(contractDate, customerName, customerEmail, vehicle, salesTax, recordingFee, processingFee, finance);
                    return sales;

                } else if (first.equalsIgnoreCase("lease")) {
                    String contractDate = fields[1];
                    String customerName = fields[2];
                    String customerEmail = fields[3];
                    int carID = Integer.parseInt(fields[4]);
                    int year = Integer.parseInt(fields[5]);
                    String make = fields[6];
                    String model = fields[7];
                    String vehicleType = fields[8];
                    String color = fields[9];
                    int odometer = Integer.parseInt(fields[10]);
                    double price = Double.parseDouble(fields[11]);
                    double endingValue = Double.parseDouble(fields[12]);
                    double leaseFee = Double.parseDouble(fields[13]);
                    double totalCost = Double.parseDouble(fields[14]);
                    double monthlyPayment = Double.parseDouble(fields[15]);

                    Vehicle vehicle = new Vehicle(carID, year, make, model, vehicleType, color, odometer, price);

                    lease = new LeaseContract(contractDate, customerName, customerEmail, vehicle, endingValue, leaseFee, monthlyPayment);
                    return lease;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}