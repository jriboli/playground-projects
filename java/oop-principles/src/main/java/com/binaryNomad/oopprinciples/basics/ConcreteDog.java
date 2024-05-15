package com.binaryNomad.oopprinciples.basics;

/***
 * Can be instantiated
 * Can extend an abstract class and/or implement interfaces
 * Much provide implementations for all abstract methods defined in abstract classes
 * it extends and interfaces it implements
 */
public class ConcreteDog implements AnimalInterface{
    /**
     * Part of Encapsulation
     * The data and associated methods are bundled together.
     * User does not have direct access to change the data.
     */
    private Integer numOfLegs = 4;

    @Override
    public void move() {
        System.out.println("Walk on all " + numOfLegs + " legs.");
    }

    @Override
    public void eat() {
        System.out.println("Chomp on kibble. Nom Nom.");
    }
}
