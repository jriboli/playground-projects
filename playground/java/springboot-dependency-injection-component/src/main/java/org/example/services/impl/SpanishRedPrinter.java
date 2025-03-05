package org.example.services.impl;

import org.example.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class SpanishRedPrinter implements RedPrinter {

    @Override
    public String print() {
        return "rojo";
    }
}
