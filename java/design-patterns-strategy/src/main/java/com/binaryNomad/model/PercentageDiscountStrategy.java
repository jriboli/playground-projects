package com.binaryNomad.model;

import java.io.Serializable;

public class PercentageDiscountStrategy implements DiscountStrategy {

    private double discountPercentage;

    public PercentageDiscountStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    @Override
    public double applyDiscount(double totalPrice) {
        return totalPrice * (1 - discountPercentage/100);
    }
}
