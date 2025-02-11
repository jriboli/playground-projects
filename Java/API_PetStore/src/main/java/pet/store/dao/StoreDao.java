package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Store;

public interface StoreDao extends JpaRepository<Store, Long>{

}
