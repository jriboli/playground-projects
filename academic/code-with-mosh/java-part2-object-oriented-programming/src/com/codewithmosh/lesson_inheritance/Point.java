package com.codewithmosh.lesson_inheritance;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        //Can check reference
        if(this == obj)
            return true;

        //Downcasting ??
        if(!(obj instanceof Point))
            return false;

        var other = (Point)obj;
        return other.x == x && other.y == y;
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        return Objects.hash(x,y);
    }
}
