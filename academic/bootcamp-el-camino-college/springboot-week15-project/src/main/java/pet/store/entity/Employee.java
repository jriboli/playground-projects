package pet.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String jobTitle;
	
	// These are to help print in JSON 
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//@ManyToOne(cascade = CascadeType.ALL)
	// Removed the cascade type it was trying to delete the Store when an employee was removed
	@ManyToOne
	
	// Is this JoinColumn annotation needed - ???
	// -- Notes from BreakOut videos
	// This helps JPA know how to look up PetStore
	@JoinColumn(name = "pet_store_id", nullable = false)
	// This name needed to match the "MappedBy" field in the PetStore class
	// -- Notes from BreakOut videos:
	// It does need to match. The field name 'petStore' needs to match the 'MappedBy'. 
    private PetStore petStore;
	
}
