package com.binaryNomad.lambda.basics;

import java.util.Comparator;

// Reference to an instance method
public class Bicycle {
    private String brand;
    private Integer framesize;
    
    public Bicycle(String brand, Integer framesize) {
        this.brand = brand;
        this.framesize = framesize;
    }
    
    public Integer getFramesize() {
        return this.framesize;
    }
}

