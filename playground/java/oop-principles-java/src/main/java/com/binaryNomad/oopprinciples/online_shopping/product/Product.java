package com.binaryNomad.oopprinciples.online_shopping.product;

import lombok.Data;

// Inheritance
@Data
public abstract class Product {
    private double price;
    private String name;
    private String description;

    public Product(double price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
