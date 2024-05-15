package com.binaryNomad.oopprinciples.library_management;

public interface Rating extends Comparable<Rating> {

    static final Integer MAX_STARS = 5;

    public Double getOverallRating();
}
