package com.binaryNomad.NPC.model;

import java.util.List;

public class GivingNPC extends BaseNPC implements AttitudeStrategy{

    private double discount = 20;

    public GivingNPC() {
        greetPhrases = List.of(
            "Hey there! Hope you're having a fantastic day!",
            "Hi! Sending positive vides your way for a wonderful day ahead!",
            "Good afternoon! Wish you lots of smiles and joy."
        );
    }

    @Override
    public double buyItem(double itemPrice) {
        return itemPrice;
    }

    @Override
    public double sellItem(double itemPrice) {
        return itemPrice - (itemPrice * (discount/100));
    }

    @Override
    public String gift() {
        return null;
    }
}
