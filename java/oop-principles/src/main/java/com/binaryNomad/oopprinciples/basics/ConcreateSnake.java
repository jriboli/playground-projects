package com.binaryNomad.oopprinciples.basics;

public class ConcreateSnake extends AbstractAnimal{
    @Override
    protected void move() {
        System.out.println("Slither around.");
    }

    @Override
    protected void eat() {
        System.out.println(processToEat());
    }

    /**
     * Part of Abstraction
     * The user only has access to essential features. They not need to know the details of how.
     *
     * Think of it like they dont need to know each step, and how to
     * execute each step
     */
    private String processToEat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Coil.");
        stringBuilder.append("Strike.");
        stringBuilder.append("Swallow.");

        return stringBuilder.toString();
    }
}
