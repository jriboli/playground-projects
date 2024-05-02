package com.binaryNomad.caching.example;

import com.binaryNomad.caching.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

    @Cacheable(value="books", keyGenerator="customKeyGenerator")
    public List<Book> getBoots() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "The Conterfeiters", "Andre Gide"));
        books.add(new Book(2, "Peer Gynt and Hedda Gabler", "Henrik Ibsen"));

        return books;
    }
}
