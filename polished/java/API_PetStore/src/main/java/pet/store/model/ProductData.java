package pet.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductData {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductData(Product product) {
        productId = product.getProductId();
        name = product.getName();;
        description = product.getDescription();
        price = product.getPrice();
    }
}
