package com.binaryNomad.oopprinciples.online_shopping.product;

import java.util.List;

public class Cellphone extends Product{
    private List<String> supportedCarriers;

    public Cellphone(double price, String name, String description, List<String> supportedCarriers) {
        super(price, name, description);
        this.supportedCarriers = supportedCarriers;
    }
}
