package com.binaryNomad.lambda.basics;

import java.util.Comparator;

public class BicycleComparator implements Comparator<Bicycle> {
    
    @Override
    public int compare(Bicycle a, Bicycle b) {
        return a.getFramesize().compareTo(b.getFramesize());
    }
}
