package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pet.store.model.InventoryData;
import pet.store.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /*
     * INVENTORY --------------------------------------------------------------------------------------
     */

    @GetMapping()
    public List<InventoryData> pullAllInventory() {
        log.info("Pulling all inventory");

        return inventoryService.findAllInventory();
    }

    @GetMapping("/stores/{storeId}")
    public List<InventoryData> pullInventoryForStore(@PathVariable Long storeId) {
        log.info("PUlling Store {} inventory", storeId);

        return inventoryService.findInventoryByStore(storeId);
    }

    @PostMapping("/stores/{storeId}")
    public InventoryData addInventory(@PathVariable Long storeId, @RequestBody InventoryData inventoryData) {
        log.info("Add new inventory to Store {}", storeId);

        return inventoryService.addInventory(storeId, inventoryData);
    }

    @PutMapping("stores/{id}/inventory/{inventoryId}/")
    public InventoryData updateInventory(@PathVariable Long id, @PathVariable Long inventoryId, @RequestBody InventoryData inventoryData ) {
        log.info("Updating the inventory counts");

        return inventoryService.updateInventory(inventoryId, inventoryData.getQuantity());
    }
}
