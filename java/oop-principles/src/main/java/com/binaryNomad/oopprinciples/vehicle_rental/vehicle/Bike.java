package com.binaryNomad.oopprinciples.vehicle_rental.vehicle;

import lombok.Getter;

@Getter
public class Bike extends Vehicle{
    private boolean hasCarrier;

    public Bike(String registrationNumber, String model, double rentalRate, boolean hasCarrier) {
        super(registrationNumber, model, rentalRate);
        this.hasCarrier = hasCarrier;
    }

    @Override
    public void accelerate() {
        System.out.println("Bike is accelerating");
    }

    @Override
    public void brake() {
        System.out.println("Bike is braking");
    }
}
