package com.binaryNomad.oopprinciples.online_shopping.payment;

// Encapsulation
public class GiftCard implements Payment{
    private String cardNumber;
    private String pin;
    private String expirationDate;

    public GiftCard(String cardNumber, String pin, String expirationDate) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean valid() {
        return cardNumber.length() == 15;
    }

    @Override
    public void charge(double amount) {
        // Fake calling credit bank
        System.out.println("Charging card " + cardNumber.substring(11) + " for $" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Returning $" + amount + "back to gift card.");

    }
}
