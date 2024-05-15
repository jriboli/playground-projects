package com.binaryNomad.oopprinciples.online_shopping.product;

public class GameConsole extends Product{
    private String brand;
    private int onboardStorage;

    public GameConsole(double price, String name, String description, String brand, int onboardStorage) {
        super(price, name, description);
        this.brand = brand;
        this.onboardStorage = onboardStorage;
    }
}
