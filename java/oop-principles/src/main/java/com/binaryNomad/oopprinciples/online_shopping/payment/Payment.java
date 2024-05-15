package com.binaryNomad.oopprinciples.online_shopping.payment;

// Abstraction
public interface Payment {

    boolean valid();
    void charge(double amount);
    void refund(double amount);
}
