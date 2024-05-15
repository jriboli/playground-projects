package com.binaryNomad.oopprinciples.vehicle_rental;

import java.util.ArrayList;
import java.util.List;

public class RentalAgency {
    private String name;
    private List<Vehicle> vehicles;

    public RentalAgency(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void rentVehicle(Customer customer, Vehicle vehicle) {
        if(vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            customer.rentVehicle(vehicle);
            System.out.println(customer.getName() + " has rented " + vehicle.getModel());
        } else {
            System.out.println("Vehicle is not available for rent");
        }
    }

    public void returnVehicle(Customer customer, Vehicle vehicle) {
        if(customer.getRentedVehicles().contains(vehicle)){
            vehicles.add(vehicle);
            customer.returnVehicle(vehicle);
            System.out.println(customer.getName() + " has returned " + vehicle.getModel());
        } else {
            System.out.println("Customer does not have that vehicle to return");;
        }

    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicles;
    }
}
