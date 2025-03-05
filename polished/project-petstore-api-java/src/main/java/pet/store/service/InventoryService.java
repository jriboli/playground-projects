package pet.store.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.store.dao.InventoryDao;
import pet.store.dao.ProductDao;
import pet.store.entity.Inventory;
import pet.store.model.InventoryData;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Handler;

@Service
@Slf4j
public class InventoryService {

    private InventoryDao inventoryDao;
    private ProductDao productDao;

    public InventoryService(InventoryDao inventoryDao, ProductDao productDao) {
        this.inventoryDao = inventoryDao;
        this.productDao = productDao;
    }
    /*
     * INVENTORY --------------------------------------------------------------------------------------
     */

    public List<InventoryData> findAllInventory() {
        List<Inventory> inventories = inventoryDao.findAll();
        List<InventoryData> results = new ArrayList<>();
        inventories.forEach(inventory -> results.add(new InventoryData(inventory)));

        return results;
    }

    public List<InventoryData> findInventoryByStore(Long storeId) {
        List<Inventory> inventories = inventoryDao.findByStoreStoreId(storeId);

        List<InventoryData> results = new ArrayList<>();
        inventories.forEach(inventory -> results.add(new InventoryData(inventory)));

        return results;
    }

    @Transactional(readOnly = false)
    public InventoryData addInventory(Long storeId, InventoryData inventoryData) {
        Long inventoryId = inventoryData.getInventoryId();
        Inventory inventory = findOrCreateInventory(inventoryId);

        setFieldsInInventory(inventory, inventoryData);

        return new InventoryData(inventoryDao.save(inventory));
    }

    public InventoryData updateInventory(Long inventoryId, int quantity) {
        Inventory inventory = findInventoryById(inventoryId);
        inventory.setQuantity(quantity);

        return new InventoryData(inventoryDao.save(inventory));
    }

    private void setFieldsInInventory(Inventory inventory, InventoryData inventoryData) {
        inventory.setInventoryId(inventoryData.getInventoryId());
        inventory.setStore(inventoryData.getStore());
        inventory.setProduct(inventoryData.getProduct());
        inventory.setLocationInStore(inventoryData.getLocationInStore());
        inventory.setQuantity(inventoryData.getQuantity());
    }

    private Inventory findOrCreateInventory(Long inventoryId) {
        Inventory inventory;
        if(Objects.isNull(inventoryId)) {
            inventory = new Inventory();
        }
        else {
            inventory = findInventoryById(inventoryId);
        }

        return inventory;
    }

    private Inventory findInventoryById(Long inventoryId) {
        return inventoryDao.findById(inventoryId).orElseThrow(() -> new NoSuchElementException("Inventory with ID=" + inventoryId + " was not found."));
    }
}
