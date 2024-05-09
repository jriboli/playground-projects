package com.binaryNomad.productservice.command.api.events;

import com.binaryNomad.productservice.command.api.entity.ProductEntity;
import com.binaryNomad.productservice.command.api.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository repository;

    public ProductEventsHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity product = new ProductEntity();
        BeanUtils.copyProperties(event,product);

        repository.save((product));
    }
}
