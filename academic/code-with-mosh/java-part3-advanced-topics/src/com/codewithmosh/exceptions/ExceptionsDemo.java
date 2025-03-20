package com.codewithmosh.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionsDemo {

    public static void show() {
        //sayHello(null); // NullPointerException
    };

    public static void show_CheckedExceptions() {
        //var reader = new FileReader("file.txt"); // Unhandled exception FileNoteFoundException

        // Proper way to handle 'Checked' exceptions
        try{
            var reader = new FileReader("file.txt");
            // We never see the below becuase code jumps to exception block
            System.out.println("File opened");
        }
        catch (FileNotFoundException ex) {
            System.out.println("File does not exist");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void show_MultipleExceptions() {
        try {
            var reader = new FileReader("file.txt");
            var value = reader.read(); // IOException
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println("Could not read data");
        }

        // Order matters for catch when Exceptions extends another
    }

    public static void show_WithFinally() {
        FileReader reader = null;
        try {
            reader = new FileReader("file.txt");
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void show_BetterHandlingOfResources() {
        try (
                var reader = new FileReader("file.txt");
                var write = new FileWriter("...");
            ) {
            var value = reader.read();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void show_WithThrownException(){
        var account = new Account();
        account.deposit(-1);
    }
    public static void sayHello(String name){
        System.out.println(name.toUpperCase());
    }
}
