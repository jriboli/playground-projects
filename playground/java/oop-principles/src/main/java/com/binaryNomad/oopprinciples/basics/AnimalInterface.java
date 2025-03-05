package com.binaryNomad.oopprinciples.basics;

import org.yaml.snakeyaml.scanner.Constant;

/***
 * When to use an Interface:
 * - Consider using the interface when our problem makes the statement "A is capable of [doing this]"
 *
 * Can be implemented by classes
 * Contains only abstract method signature (i.e. sort of)
 * Cannot have method bodies until Java 8, with default and static methods
 * Allows multiple inheritance of type (i.e a class can implement multiple interfaces)
 * Provides a way to define a contract for classes to implement it
 */

public interface AnimalInterface {
    /***
     * Interfaces:
     *
     * Can have a collection of field constants
     * and method signature.
     */

    // Final means that the value cannot be changed after initialization, that's what makes it a constant.
    public final boolean SOUL = true;
    // Static Final means only one instance of the variable no matter how many objects are created.

    /***
     * v8 - Methods in an interface are implicitly abstract if they are not
     * static or default and all are public
     *
     * v9 - Can add private methods
     */

    // Static methods provide utility methods that are relevant to the interface
    // Part of interface contract, but not inherited by subclasses
    // Useful for helper methods or factory methods
    public static boolean hasSoul(){
        return SOUL;
    }

    public void move();

    public void eat();
}
