package com.codewithmosh.lesson_inheritance;

public class TextBox extends UIControl {
    private String text = "";

    public TextBox() {
        //super(true);
        System.out.println("TextBox");
    }

    @Override
    public void render() {
        //super.render();
        System.out.println("Render TextBox");
    }

    @Override
    public String toString(){
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void clear(){
        text = "";
    }
}
