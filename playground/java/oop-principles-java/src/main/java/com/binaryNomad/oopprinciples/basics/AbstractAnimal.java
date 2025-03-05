package com.binaryNomad.oopprinciples.basics;

/***
 * When to use an Abstract Class
 * - Consider using abstract classes and inheritance when our problem makes
 * the evidence "A is a B"
 *
 * Can be extended by other classes
 * Can have both abstract and concrate methods
 * Cannot be instantiated
 * Allows single inheritance only (i.e. a class can only extend one abstract class)
 * Provide a partial implementation of functionality that subclasses can override/extend
 */
public abstract class AbstractAnimal {

    /***
     * Abstract classes have no restriction on field and method modifiers
     * Can also have constructors - which execute during child instantiation
     */

    // Why does SOUL need to be static here and not in an interface??
    public static final boolean SOUL = true;

    /***
     * Declare methods signatures using 'abstract' keyword
     * to force subclasses to implement
     */

    // Static methods in abstract classes can provide utility methods related to the class functionality
    // Related to the class itself, rather than with the instance of the class
    // Can be invoked with class name without an instance
    // Can be overridden in subclasses
    public static boolean hasSoul(){
        return SOUL;
    }

    protected abstract void move();

    protected abstract void eat();
}
