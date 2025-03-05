package com.binaryNomad.cache_getting_started.repository;

import com.binaryNomad.cache_getting_started.model.Book;

public interface BookRepository {

    Book getByIsbn(String isbn);
}
