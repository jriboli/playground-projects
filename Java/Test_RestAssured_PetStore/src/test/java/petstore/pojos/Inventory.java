package petstore.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Inventory {

    private Long inventoryId;
    private int quantity;
    private String locationInStore;
    private int reorderLevel;
    private Product product;
    private Store store;
}
