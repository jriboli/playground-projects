package org.example;

import org.example.services.ColourPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ColoursApplication implements CommandLineRunner {

    private ColourPrinter colourPrinter;

    public ColoursApplication(ColourPrinter colourPrinter) {
        this.colourPrinter = colourPrinter;
    }

    public static void main(String[] args) {
        SpringApplication.run(ColoursApplication.class, args);
    }

    @Override
    public void run(final String... args) {
        // Easy way to find beans for Depenedency Injection is the 'new' keyword
        // final ColourPrinter colourPrinter = new ColourPrinterImpl();
        System.out.println(colourPrinter.print());
    }
}