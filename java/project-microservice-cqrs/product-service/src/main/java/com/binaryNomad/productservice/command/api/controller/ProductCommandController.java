package com.binaryNomad.productservice.command.api.controller;

import com.binaryNomad.productservice.command.api.commands.CreateProductCommand;
import com.binaryNomad.productservice.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    private CommandGateway gateway;

    public ProductCommandController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel product) {
        CreateProductCommand command = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        String result = gateway.sendAndWait(command);
        return result;
    }
}
