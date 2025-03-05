package com.binaryNomad.pizza_market;

import com.binaryNomad.pizza_market.models.CheeseDecorator;
import com.binaryNomad.pizza_market.models.Pizza;
import com.binaryNomad.pizza_market.models.PlainPizza;
import com.binaryNomad.pizza_market.models.TomatoDecorator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(PizzaMarketApplication.class, args);
        Pizza pizza = new PlainPizza();

        pizza = new CheeseDecorator(pizza);

        pizza = new TomatoDecorator(pizza);

        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: $" + pizza.getCost());
    }
}