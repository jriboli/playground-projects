package com.binaryNomad.caching.eviction.controllers;

import com.binaryNomad.caching.eviction.service.BookService;
import com.binaryNomad.caching.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<String> getBooks() {

        bookService.getBooks();
        return ResponseEntity.ok("Found 'em");
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<String> getBookByIsbn(
            @PathVariable int id
    ) {

        bookService.getBookById(id);
        return ResponseEntity.ok("Found it");
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(
            @RequestBody Book book
    ) {

        bookService.saveBook(book);
        return ResponseEntity.ok("Added");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable int id,
            @RequestBody Book book
    ) {
        book.setId(id);
        bookService.saveBook(book);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable int id
    ) {

        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/cache")
    public ResponseEntity<String> examineCache() {
        bookService.showCache();
        return ResponseEntity.ok("Got 'em");
    }
}
