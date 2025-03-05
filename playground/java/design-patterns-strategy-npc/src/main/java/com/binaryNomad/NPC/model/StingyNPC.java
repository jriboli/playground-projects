package com.binaryNomad.NPC.model;

import java.util.List;

public class StingyNPC extends BaseNPC implements AttitudeStrategy{

    private double upsalePercentage = 15;
    private double cashOnHand = 100;

    public StingyNPC() {
        greetPhrases = List.of(
            "Speak up!",
            "What now?",
            "Out with it!"
        );
    }

    @Override
    public double buyItem(double itemPrice) {
        if (cashOnHand > 200)
            return itemPrice / 2;

        return itemPrice;
    }

    @Override
    public double sellItem(double itemPrice) {
        return itemPrice + (itemPrice * (upsalePercentage/100));
    }

    @Override
    public String gift() {
        return null;
    }
}
