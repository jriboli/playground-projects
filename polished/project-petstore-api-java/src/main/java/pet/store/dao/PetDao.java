package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.store.entity.Pet;

public interface PetDao extends JpaRepository<Pet, Long> {

}
