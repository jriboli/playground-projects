package pet.store.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Inventory;
import pet.store.entity.Store;
import pet.store.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class StoreInventoryData {

    private Long storeId;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String name;
    private String phone;

    private Set<InventoryResponse> inventories = new HashSet<>();

    public StoreInventoryData(Store store) {
        storeId = store.getStoreId();
        address = store.getAddress();
        city = store.getCity();
        state = store.getState();
        zip = store.getZip();
        name = store.getName();
        phone = store.getPhone();

        for(Inventory inventory : store.getInventories()) {
            inventories.add(new StoreInventoryData.InventoryResponse(inventory));
        }
    }

    @Data
    @NoArgsConstructor
    static class InventoryResponse {
        private Long inventoryId;
        private int quantity;
        private String locationInStore;
        private int reorderLevel;
        private Product product;
        private Store store;

        public InventoryResponse(Inventory inventory) {
            inventoryId = inventory.getInventoryId();
            quantity = inventory.getQuantity();
            locationInStore = inventory.getLocationInStore();
            reorderLevel = inventory.getReorderLevel();
            product = inventory.getProduct();
            store = inventory.getStore();

        }
    }
}
