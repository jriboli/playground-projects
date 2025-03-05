package pet.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Inventory;
import pet.store.entity.Store;
import pet.store.entity.Product;

@Data
@NoArgsConstructor
public class InventoryData {

    private Long inventoryId;
    private int quantity;
    private String locationInStore;
    private int reorderLevel;
    private Product product;
    private Store store;

    public InventoryData(Inventory inventory) {
        inventoryId = inventory.getInventoryId();
        quantity = inventory.getQuantity();
        locationInStore = inventory.getLocationInStore();
        reorderLevel = inventory.getReorderLevel();
        product = inventory.getProduct();
        store = inventory.getStore();
    }

    @Data
    @NoArgsConstructor
    static class StoreResponse {
        private Long storeId;
        private String address;
        private String city;
        private String state;
        private String zip;
        private String name;
        private String phone;

        public StoreResponse(Store store) {
            this.storeId = store.getStoreId();
            this.address = store.getAddress();
            this.city = store.getCity();
            this.state = store.getState();
            this.zip = store.getZip();
            this.name = store.getName();
            this.phone = store.getPhone();
        }
    }
}
