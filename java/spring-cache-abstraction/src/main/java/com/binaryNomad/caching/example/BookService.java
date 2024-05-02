package com.binaryNomad.caching.example;

import com.binaryNomad.caching.eviction.service.CachingService;
import com.binaryNomad.caching.model.Book;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class BookService {

    private List<Book> books = new ArrayList<>();

    private CacheManager cacheManager;

    public BookService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;

        books.add(new Book(1, "The Conterfeiters", "Andre Gide"));
        books.add(new Book(2, "Peer Gynt and Hedda Gabler", "Henrik Ibsen"));
    }

    @Cacheable("books")
    public List<Book> getBooks() {
        log.info("Retrieving all books from data source.");
        return books;
    }

    @Cacheable(cacheNames="book", key="#id")
    public Book getBookById(int id) {
        log.info("Retrieving book from data source.");
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No book found with that ID"));
    }

    @CachePut(cacheNames="book", key="#book.id")
    public Book saveBook(Book book) {
        log.info("Saving book from data source.");
        books.add(book);
        return book;
    }

    @CacheEvict(cacheNames="book", key="#id")
    public void deleteBook(int id) {
        log.info("Deleting book from data source.");
        Book book = getBookById(id);
        books.remove(book);
    }

    public void showCache() {
        cacheManager.getCacheNames().forEach(c -> System.out.println("Cache Name: " + c));

        Cache cache = cacheManager.getCache("book");
        for (int i = 0; i < 10; i++) {
            System.out.println("Cache Value: " +cache.get(i));
        }
    }
}
