package com.binaryNomad.oopprinciples.vehicle_rental;

import lombok.Getter;

import javax.print.DocFlavor;

@Getter
public abstract class Vehicle {

    private String registrationNumber;
    private String model;
    private double rentalRate;

    public Vehicle(String registrationNumber, String model, double rentalRate) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.rentalRate = rentalRate;
    }

    public abstract void accelerate();
    public abstract void brake();
}
