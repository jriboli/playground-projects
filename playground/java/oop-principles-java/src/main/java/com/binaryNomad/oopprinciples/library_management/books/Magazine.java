package com.binaryNomad.oopprinciples.library_management.books;

import com.binaryNomad.oopprinciples.library_management.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Magazine extends Book implements Rating {
    private String publisher;

    public Magazine(String title, Integer numOfPages, String publisher) {
        this.title = title;
        this.numOfPages = numOfPages;
        this.publisher = publisher;
    }

    @Override
    public String getTitle() {
        return title + " by " + publisher;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public void addRating() {
        // TBD
    }

    @Override
    public Double getOverallRating() {
        return 2.5;
    }

    @Override
    public int compareTo(Rating that) {
        return this.getOverallRating().compareTo(that.getOverallRating());
    }
}
