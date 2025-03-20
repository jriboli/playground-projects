package com.codewithmosh.lesson_interfaces;

public class TaxCalculator2019 implements TaxCalculator{
    private double taxableIncome;

    public TaxCalculator2019(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    // Not needed, but should have to help keep Class and Interface inline
    @Override
    public double calculateTax(){
        return taxableIncome * 0.4;
    }
}
