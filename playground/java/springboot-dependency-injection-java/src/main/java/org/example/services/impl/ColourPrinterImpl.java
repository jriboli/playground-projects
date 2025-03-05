package org.example.services.impl;

import org.example.services.BluePrinter;
import org.example.services.ColourPrinter;
import org.example.services.GreenPrinter;
import org.example.services.RedPrinter;

import java.util.GregorianCalendar;

public class ColourPrinterImpl implements ColourPrinter {
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    // This tells Spring Boot to find the redPrint Bean and place it here on construction
    public ColourPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        //this.redPrinter = new EnglishRedPrinter();
        //this.bluePrinter = new EnglishBluePrinter();
        //this.greenPrinter = new EnglishGreenPrinter();
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}
