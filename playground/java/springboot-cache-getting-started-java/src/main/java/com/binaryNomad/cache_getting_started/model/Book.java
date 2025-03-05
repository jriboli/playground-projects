package com.binaryNomad.cache_getting_started.model;

import jakarta.annotation.security.DenyAll;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Book {

    private String isbn;
    private String title;
}
