package com.binaryNomad.oopprinciples.vehicle_rental.vehicle;

import lombok.Getter;

@Getter
public class Car extends Vehicle{
    private int seatingCapacity;

    public Car(String registrationNumber, String model, double rentalRates, int seatingCapacity) {
        super(registrationNumber, model, rentalRates);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void accelerate() {

    }

    @Override
    public void brake() {

    }
}
