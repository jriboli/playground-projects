package com.binaryNomad.productservice.command.api.repository;

import com.binaryNomad.productservice.command.api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
