package com.binaryNomad.oopprinciples.library_management.books;

import lombok.AllArgsConstructor;

// Abstraction
public abstract class Book {

    protected String title;
    protected Integer numOfPages;

    public abstract String getTitle();
    public abstract void setTitle(String title);
    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        if (numOfPages <= 0) {
            throw new IllegalArgumentException("Number of pages must be positive");
        }
        this.numOfPages = numOfPages;
    }

}
