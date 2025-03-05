package com.binaryNomad.strategyPattern;


import com.binaryNomad.strategyPattern.model.FlatDiscountStrategy;
import com.binaryNomad.strategyPattern.model.PercentageDiscountStrategy;
import com.binaryNomad.strategyPattern.service.BillingSystem;

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