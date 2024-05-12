package com.binaryNomad;


import com.binaryNomad.model.FlatDiscountStrategy;
import com.binaryNomad.model.PercentageDiscountStrategy;
import com.binaryNomad.service.BillingSystem;

public class StrategyPatternApplication {
    public static void main(String[] args) {
        BillingSystem billingSystem = new BillingSystem();

        billingSystem.setDiscountStrategy(new FlatDiscountStrategy(50));
        double totalWithFlatDiscount = billingSystem.calculateTotal(200);
        System.out.println("Total with flat discount: $" + totalWithFlatDiscount);

        billingSystem.setDiscountStrategy(new PercentageDiscountStrategy(10));
        double totalWithPercentageDiscount = billingSystem.calculateTotal(200);
        System.out.println("Total with percentage discount: $" + totalWithPercentageDiscount);

    }
}