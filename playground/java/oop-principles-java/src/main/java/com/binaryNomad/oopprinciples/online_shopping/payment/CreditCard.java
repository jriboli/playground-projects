package com.binaryNomad.oopprinciples.online_shopping.payment;

public class CreditCard implements Payment{
    private String cardNumber;
    private String pin;
    private String expirationDate;

    public CreditCard(String cardNumber, String pin, String expirationDate) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean valid() {
        if(cardNumber.length() > 15 || pin == "1234")
            return false;

        return true;
    }

    @Override
    public void charge(double amount) {
        System.out.println("Applying charge for $" + amount + " to credit card.");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refund for $" + amount);
    }
}
