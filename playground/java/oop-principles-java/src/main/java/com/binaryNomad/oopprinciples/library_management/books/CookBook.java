package com.binaryNomad.oopprinciples.library_management.books;

import com.binaryNomad.oopprinciples.library_management.Author;
import com.binaryNomad.oopprinciples.library_management.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CookBook extends Book implements Rating {
    private Integer tableOfContentPages;
    private Integer numOfRecipes;
    private List<Author> authors;
    private List<Integer> dishesTriedPerPerson;

    public CookBook(String title, Integer numOfPages, Integer tableOfContentPages, Integer numOfRecipes,
                    List<Author> authors, List<Integer> dishesTriedPerPerson) {
        this.title = title;
        this.numOfPages = numOfPages;
        this.tableOfContentPages = tableOfContentPages;
        this.numOfRecipes = numOfRecipes;
        this.authors = authors;
        this.dishesTriedPerPerson = dishesTriedPerPerson;
    }

    @Override
    public String getTitle() {
        if (authors == null || authors.isEmpty()) {
            return title;
        }
        return title + " with " + authors.stream()
                .map(Author::getFirstName)
                .reduce("", (acc, name) -> acc + ", " + name);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages - tableOfContentPages;
    }

    public void addDishesTried(Integer numOfDishesTried) {
        if(numOfDishesTried > numOfRecipes)
            throw new ArrayIndexOutOfBoundsException("More dishes tried than exist in cook book.");

        dishesTriedPerPerson.add(numOfDishesTried);
    }

    @Override
    public Double getOverallRating() {
        if (dishesTriedPerPerson.isEmpty()) {
            return 0.0;
        }

        Integer sumOfAllDishesTried = 0;

        for(Integer i : dishesTriedPerPerson) {
            sumOfAllDishesTried += i;
        }

        return (double) sumOfAllDishesTried / (numOfRecipes * dishesTriedPerPerson.size());
    }

    @Override
    public int compareTo(Rating that) {
        return this.getOverallRating().compareTo(that.getOverallRating());
    }
}
