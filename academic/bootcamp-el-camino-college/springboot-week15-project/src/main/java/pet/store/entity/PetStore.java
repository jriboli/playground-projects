package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class PetStore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long petStoreId;
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// MappedBy is like naming it for access in the linked class
	// Note here "petStore" is used in the Employee class
	// -- Notes from BreakOUt videos:
	// Bidirectional one-to-many 
	@OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
	// I wonder if the Class Type here matters for the linking ???
	// -- Note from BreakOut videos:
	// Yes, the class type matters
	private Set<Employee> employees = new HashSet<>();
	// Note: Can use LIST but SET has 'add' and it will add or replace the item. List you have to look up first to find correct item. 
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// This MappedBy needed to match the ManyToMany in PetStore class - WHY ???
	@ManyToMany(mappedBy = "petStores", cascade = CascadeType.ALL)
	private Set<Customer> petStoreCustomers = new HashSet<>();
	
}
