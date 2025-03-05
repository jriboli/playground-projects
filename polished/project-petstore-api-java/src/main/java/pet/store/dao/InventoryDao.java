package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.Employee;
import pet.store.entity.Inventory;

import java.util.List;

public interface InventoryDao extends JpaRepository<Inventory, Long> {

    List<Inventory> findByStoreStoreId(Long storeId);
}
