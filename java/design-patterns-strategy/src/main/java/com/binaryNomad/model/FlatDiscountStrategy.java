package com.binaryNomad.model;

public class FlatDiscountStrategy implements DiscountStrategy{

    private double discountAmount;

    public FlatDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double applyDiscount(double totalPrice) {
        return totalPrice - discountAmount;
    }
}
