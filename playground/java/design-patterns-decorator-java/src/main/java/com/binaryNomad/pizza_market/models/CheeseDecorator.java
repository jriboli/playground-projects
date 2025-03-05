package com.binaryNomad.pizza_market.models;

public class CheeseDecorator extends PizzaDecorator{

    public CheeseDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    public String getDescription() {
        return super.getDescription() + ", Cheese";
    }

    public double getCost() {
        return super.getCost() + 1.0;
    }
}
