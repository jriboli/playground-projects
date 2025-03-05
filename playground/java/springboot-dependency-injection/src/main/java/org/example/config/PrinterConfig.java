package org.example.config;

import org.example.services.ColourPrinter;
import org.example.services.impl.*;
import org.example.services.BluePrinter;
import org.example.services.RedPrinter;
import org.example.services.GreenPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Tell Spinrg Boot to look in this class for bean declaration
@Configuration
public class PrinterConfig {

    // Bean => Concrete Class
    @Bean
    public BluePrinter bluePrinter() {
        return new EnglishBluePrinter();
    }

    @Bean
    public RedPrinter redPrinter() {
        return new SpanishRedPrinter();
    }

    @Bean
    public GreenPrinter greenPrinter() {
        return new EnglishGreenPrinter();
    }

    // This has Spring Boot when creating this bean it find the redPrinter bean and places it here, etc.
    @Bean
    public ColourPrinter colourPrinter(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        return new ColourPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}
