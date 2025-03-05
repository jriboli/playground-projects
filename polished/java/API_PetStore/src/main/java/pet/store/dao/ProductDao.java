package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
