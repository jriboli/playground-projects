package com.binaryNomad.pizza_market.models;

public class TomatoDecorator extends PizzaDecorator{
    public TomatoDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Tomato";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
