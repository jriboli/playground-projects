package com.binaryNomad.cache_getting_started.repository;

import com.binaryNomad.cache_getting_started.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleBookRepository implements BookRepository{

    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        log.info("Retrieving book");
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    private void simulateSlowService() {
        try{
            long time = 6000L;
            Thread.sleep(time);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
