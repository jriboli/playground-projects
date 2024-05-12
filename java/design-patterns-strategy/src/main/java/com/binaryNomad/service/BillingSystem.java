package com.binaryNomad.service;

import com.binaryNomad.model.DiscountStrategy;

import java.util.Objects;

public class BillingSystem {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateTotal(double totalPrice) {
        if(Objects.isNull(discountStrategy)){
            return totalPrice;
        }

        return discountStrategy.applyDiscount(totalPrice);
    }
}
