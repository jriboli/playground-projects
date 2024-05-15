package com.binaryNomad.oopprinciples.vehicle_rental.vehicle;

import lombok.Getter;

@Getter
public class Truck extends Vehicle{
    private double loadCapacity;

    public Truck(String registrationNumber, String model, double rentalRate, double loadCapacity) {
        super(registrationNumber, model, rentalRate);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void accelerate() {
        System.out.println("Truck is accelerating");
    }

    @Override
    public void brake() {
        System.out.println("Truck is braking");
    }
}
