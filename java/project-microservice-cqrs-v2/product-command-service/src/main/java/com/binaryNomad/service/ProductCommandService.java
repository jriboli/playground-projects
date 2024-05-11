package com.binaryNomad.service;

import com.binaryNomad.entity.Product;
import com.binaryNomad.events.ProductEvent;
import com.binaryNomad.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private ProductRepository repository;
    private KafkaTemplate<String, Object> kafkaTemplate;

    public ProductCommandService(ProductRepository repository, KafkaTemplate<String, Object> kafkaTemplate) {

        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product createProduct(Product product) {

        Product saved = repository.save(product);
        ProductEvent event = new ProductEvent("CreateProduct", saved);
        kafkaTemplate.send("product-event-topic", event);

        return saved;
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = repository.findById(id).get();

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());

        Product saved = repository.save(existing);
        ProductEvent event = new ProductEvent("UpdateProduct", saved);
        kafkaTemplate.send("product-event-topic", event);

        return saved;
    }
}
