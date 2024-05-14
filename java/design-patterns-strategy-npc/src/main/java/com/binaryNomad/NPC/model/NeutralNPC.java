package com.binaryNomad.NPC.model;

import java.util.List;

public class NeutralNPC extends BaseNPC implements AttitudeStrategy{

    public NeutralNPC() {
        greetPhrases = List.of(
            "Hello there.",
            "Good day.",
            "How can I assist you?"
        );
    }

    @Override
    public double buyItem(double itemPrice) {
        return itemPrice;
    }

    @Override
    public double sellItem(double itemPrice) {
        return itemPrice;
    }

    @Override
    public String gift() {
        return null;
    }
}
