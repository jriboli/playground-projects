package com.binaryNomad.NPC.service;

import com.binaryNomad.NPC.model.AttitudeStrategy;
import org.springframework.stereotype.Component;

@Component
public class ShopKeeper {

    private AttitudeStrategy attitudeStrategy;

    public void setAttitudeStrategy(AttitudeStrategy attitudeStrategy) {
        this.attitudeStrategy = attitudeStrategy;
    }

    public String buyItem(double itemPrice) {
        return attitudeStrategy.greetings() + ". " + "Buy for: $" + attitudeStrategy.buyItem(itemPrice);
    }

    public String sellItem(double itemPrice) {
        return attitudeStrategy.greetings() + ". " + "Sell for: $" + attitudeStrategy.sellItem(itemPrice);
    }
}
