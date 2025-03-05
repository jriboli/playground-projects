package com.binaryNomad.service;

import com.binaryNomad.entity.Product;
import com.binaryNomad.events.ProductEvent;
import com.binaryNomad.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    private ProductRepository repository;

    public ProductQueryService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent) {
        Product tempProduct = productEvent.getProduct();
        if(productEvent.getEventType().equals("CreateProduct")) {
            repository.save(tempProduct);
        }
        else if(productEvent.getEventType().equals("UpdateProduct")) {
            Product existing = repository.findById(tempProduct.getId()).get();
            existing.setName(tempProduct.getName());
            existing.setDescription(tempProduct.getDescription());
            existing.setPrice(tempProduct.getPrice());
            repository.save(existing);
        }
    }
}
