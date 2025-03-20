package com.binaryNomad.problems;

public class recursion_intro {
    /*
     * In code, recursion is implemented using a function that calls itself.
     */

    public void iterativeAlgorithm(){
        for(int i = 1; i<=10; i++) {
            System.out.println(i);
        }
    }

    public void recursiveAlgorithm(int i) {
        if (i > 10)
            return;

        System.out.println(i);
        recursiveAlgorithm(i+1);
    }

    /*
     * Pay attention to how recursion executes code. Note that a print after the
     * method call would be executed in reverse, on the way out.
     */
}
