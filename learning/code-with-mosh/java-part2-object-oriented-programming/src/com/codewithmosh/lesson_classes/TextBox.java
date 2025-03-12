package com.codewithmosh.lesson_classes;

public class TextBox {
    // Reference Types are set to NULL by default if not assigned value
    // ie. String == null
    // NullPointerException - it is not pointing to an object in memory
    // Can help reduce by initializing reference fields
    public String text = "";
    public void setText(String text){
        this.text = text;
    }
    public void clear(){
        text = "";
    }
}