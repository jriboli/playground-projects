package com.binaryNomad.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private int id;
    private String name;
    private String address;

    public Customer(final String name, final String address) {
        this.name = name;
        this.address = address;
    }

    public void setCustomerAddress(final String address) {
        this.address = name + "," + address;
    }
}
