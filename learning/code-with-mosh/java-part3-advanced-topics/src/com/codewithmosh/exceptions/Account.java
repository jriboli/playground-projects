package com.codewithmosh.exceptions;

public class Account {

    public void deposit(float value) {
        if(value <= 0)
            throw new IllegalArgumentException("value can not be 0 or less");
    }
}
