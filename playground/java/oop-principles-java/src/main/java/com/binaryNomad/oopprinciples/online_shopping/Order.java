package com.binaryNomad.oopprinciples.online_shopping;

import com.binaryNomad.oopprinciples.online_shopping.payment.Payment;

public class Order {
    private static final double TAX_RATE = 9.25;

    private Cart cart;
    private Payment payment;
    private String shippingType;
    private String address;

    private double itemsCost;
    private double shippingCost;
    private double tax;
    private double totalCost;

    public void checkOut(Cart cart){
        // confirm items in cart
        if(!cart.hasItems()) {
            throw new IllegalArgumentException("No items in cart.");
        }

        this.cart = cart;
    }

    public void addPayment(Payment payment) {
        // validate payment
        if(!payment.valid()){
            throw new IllegalArgumentException("The Payment is not valid, please select another.");
        }

        this.payment = payment;
    }

    public void addShipping(String shippingType, String address) {
        // store shippign info
        this.shippingType = shippingType;
        this.address = address;
    }

    public double calculateTotal() {
        // run the totals
        itemsCost = 0.00;
        cart.getProducts().forEach((k,v) -> itemsCost += k.getPrice());

        shippingCost = 5.00;

        tax = itemsCost * (TAX_RATE/100);

        totalCost = itemsCost + tax + shippingCost;
        return totalCost;
    }

    public void commit(){
        // charge card

        payment.charge(totalCost);

        // send details to shipping
        System.out.println("Order complete! Send to " + address);
    }
}
