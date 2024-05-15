package com.binaryNomad.oopprinciples.vehicle_rental;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Customer {
    private String name;
    private List<Vehicle> rentedVehicles;

    public Customer(String name) {
        this.name = name;
        this.rentedVehicles = new ArrayList<>();
    }

    public void rentVehicle(Vehicle vehicle) {
        rentedVehicles.add(vehicle);
    }

    public void driveVehicle(Vehicle vehicle) {
        if(rentedVehicles.contains(vehicle)){
            vehicle.accelerate();
            vehicle.brake();
        } else {
            System.out.println("Customer does not have that vehicle to drive");;
        }
    }

    public void returnVehicle(Vehicle vehicle) {
        rentedVehicles.remove(vehicle);
    }

}
