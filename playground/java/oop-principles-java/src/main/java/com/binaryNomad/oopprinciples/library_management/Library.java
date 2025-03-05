package com.binaryNomad.oopprinciples.library_management;

import com.binaryNomad.oopprinciples.library_management.books.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
public class Library {

    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public List<Book> sortBooks(){

        return books.stream().sorted().toList();
    }

    public Book findByTitle(String title) {

        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().equals(title.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No book found with that Title."));
    }

}
