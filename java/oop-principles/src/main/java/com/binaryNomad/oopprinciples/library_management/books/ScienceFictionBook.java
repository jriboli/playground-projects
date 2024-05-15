package com.binaryNomad.oopprinciples.library_management.books;

import com.binaryNomad.oopprinciples.library_management.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.InvalidParameterException;
import java.util.List;

// Encapsulation
@Data
public class ScienceFictionBook extends Book implements Rating {

    private List<String> characters;
    private List<Integer> ratings;

    public ScienceFictionBook(String title, Integer numOfPages, List<String> characters, List<Integer> ratings) {
        this.title = title;
        this.numOfPages = numOfPages;
        this.characters = characters;
        this.ratings = ratings;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void addRating(Integer starRating) {
        if (starRating < 0 || starRating > Rating.MAX_STARS)
            throw new InvalidParameterException("Rating must be between 0 and 5 stars");

        ratings.add(starRating);
    }

    @Override
    public Double getOverallRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return (double) ratings.stream().reduce(0, Integer::sum) /ratings.size();
    }

    @Override
    public int compareTo(Rating that) {
        return this.getOverallRating().compareTo(that.getOverallRating());
    }
}
