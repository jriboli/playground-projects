package com.binaryNomad.oopprinciples.online_shopping;

import com.binaryNomad.oopprinciples.online_shopping.product.Product;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Data
public class Cart {

    // Polymorphism
    private Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public boolean hasItems() {
        return !products.isEmpty();
    }

    public void addItem(Product product, int quantity) {
        if(products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeItem(Product product) {
        products.remove(product);
    }

    public void updateItemQuantity(Product product, int quantity) {
        if(products.containsKey(product)) {
            products.put(product, quantity);
        } else {
            throw new NoSuchElementException(product.getName() + " does not exist in cart.");
        }
    }

    public void displayCart() {
        products.forEach((k, v) -> System.out.println("Product: " + k.getName() + " | Quantity: " + v));
    }
}
