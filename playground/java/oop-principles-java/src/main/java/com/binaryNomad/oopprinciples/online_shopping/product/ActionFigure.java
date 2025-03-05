package com.binaryNomad.oopprinciples.online_shopping.product;

public class ActionFigure extends Product{
    private String condition;

    public ActionFigure(double price, String name, String description, String condition) {
        super(price, name, description);
        this.condition = condition;
    }
}
