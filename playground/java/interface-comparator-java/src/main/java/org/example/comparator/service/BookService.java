package org.example.comparator.service;

import org.example.comparator.entity.Book;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class BookService {
    List<Book> books = new ArrayList<>();
    public void setupBookService() {
        books.add(new Book("Harry Potter and the Philosophers Stone", "J. K. Rowlings", 9.99F));
        books.add(new Book("Harry Potter and the Chamber of Secrets", "J. K. Rowlings", 9.99F));
        books.add(new Book("Harry Potter and the Prisoner of Azkaban", "J. K. Rowlings", 11.99F));
        books.add(new Book("Harry Potter and the Goblet of Fire", "J. K. Rowlings", 22.99F));

        books.add(new Book("Killing Floor", "Lee Child", 7.99F));
        books.add(new Book("Die Trying", "Lee Child", 8.99F));
        books.add(new Book("Tripwire", "Lee Child", 9.99F));
        books.add(new Book("Echo Burning", "Lee Child", 4.99F));

        books.add(new Book("Blindsight", "Robin Cook", 12.99F));
        books.add(new Book("Contagion", "Robin Cook", 13.99F));
        books.add(new Book("Vector", "Robin Cook", 13.99F));
        books.add(new Book("Crisis", "Robin Cook", 9.99F));
    }

    public void SortByAuthor() {
        Comparator<Book> com = new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return (b1.getAuthor().compareTo(b2.getAuthor()));
            }
        };

        // Collections.sort(books, com);
        books.sort(com);

        System.out.println("-- SORT BY AUTHOR -------------------------------------------");
        for (var b : books) {
            System.out.println(b);
        }
    }

    public void SortByAuthorThenTitle() {
        Comparator<Book> com = Comparator
                .comparing((Book b) -> b.getAuthor())
                .thenComparing(b -> b.getTitle());

        books.sort(com);

        System.out.println("-- SORT BY AUTHOR THEN TITLE -------------------------------------------");
        for (var b : books) {
            System.out.println(b);
        }

    }

    public void SortByPriceThenAuthor() {
        Comparator<Book> com = Comparator
                .comparing((Book b) -> b.getPrice())
                .thenComparing(b -> b.getAuthor());

        books.sort(com);

        System.out.println("-- SORT BY PRICE THEN AUTHOR -------------------------------------------");
        for (var b : books) {
            System.out.println(b);
        }
    }

    public void FilterByAuthor(String author) {
        var results = books.stream()
                .filter(a -> Objects.equals(a.getAuthor(), author))
                .toList();

        System.out.println("-- FILTER BY AUTHOR -------------------------------------------");
        for (var b : books) {
            System.out.println(b);
        }
    }


    public void FindMedianPriceByAuthor(String author) {

        ToDoubleFunction<Book> tdf = new ToDoubleFunction<Book>() {
            public double applyAsDouble(Book value) {
                return value.getPrice();
            }
        };

        // Reduce with Lambda
        ToDoubleFunction<Book> tdf2 = b -> b.getPrice();

        // Reduce further with method call
        ToDoubleFunction<Book> tdf3 = Book::getPrice;

        var bookCount = books.stream()
                .filter(b -> Objects.equals(b.getAuthor(), author))
                .count();

        var result = books.stream()
                .filter(a -> Objects.equals(a.getAuthor(), author))
                .mapToDouble(Book::getPrice)
                .sum();

        System.out.println("-- FILTER BY AUTHOR -------------------------------------------");
        System.out.printf("For %s, there are %s books for a total cost of %.2f", author, bookCount, result);
    }
}
