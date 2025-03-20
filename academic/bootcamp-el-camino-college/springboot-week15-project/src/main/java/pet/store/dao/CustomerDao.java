package pet.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	// Remember to adapt the code to fit your specific entity and field names
	
	List<Customer> findPetStoreCustomersByPetStores_PetStoreId(Long petStoreId);
	// A lot to know about naming this method
	// find<fieldName>By<Class>_<fieldName>
	// The first <fieldName> is the name of the Customer parameter in the PetStore entity
	// The <class> is the entity you want to use to filter/search
	// The second <fieldName> is the field id in the class entity
}
