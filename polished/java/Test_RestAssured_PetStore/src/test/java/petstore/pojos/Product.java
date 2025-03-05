package petstore.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
}
