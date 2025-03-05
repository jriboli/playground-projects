package com.binaryNomad.oopprinciples.online_shopping.payment;

import java.util.Objects;

public class PayPal implements Payment{
    private String paypalId;

    public PayPal(String username, String apikey) {
        // Pretend to confirm info and store Id
        paypalId = username + apikey;
    }

    @Override
    public boolean valid() {
        // Pretend to valid the paypalId
        if(Objects.isNull(paypalId))
            return false;

        return true;
    }

    @Override
    public void charge(double amount) {
        // Fake call to Paypal
        System.out.println("Calling Paypal to charge for $" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding $" + amount + " to Paypal account.");
    }
}
