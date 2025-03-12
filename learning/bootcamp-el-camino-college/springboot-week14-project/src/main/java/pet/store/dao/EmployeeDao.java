package pet.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
	// I believe this parameter name needs to match that setup in the Employee (Entity) class
	// Update:
	// Method names follow a specific pattern. They typically start with findBy, readBy, queryBy, getBy, 
	// or countBy, followed by the property names of the entity. For example, if you have a property named store 
	// in your Employee entity, you can create a method named findByStore in the EmployeeRepository.
	List<Employee> findByPetStorePetStoreId(Long petStoreId);
}
