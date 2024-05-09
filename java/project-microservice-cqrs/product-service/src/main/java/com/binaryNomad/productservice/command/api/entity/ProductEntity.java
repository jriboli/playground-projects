package com.binaryNomad.productservice.command.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
public class ProductEntity {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
