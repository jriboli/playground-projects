package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	// Name is the TABLE name
	// JoinColumns is using our PetStore ID 
	// This is because one of the two Many to Many artifacts needs to setup this way
	// Turns out there is a "ownership" understanding needed
	// -- Notes from BreakOut videos:
	// The joinColumns attribute must name the table column, NOT Java field name
	// The inverseJoinColumns must name the table column in the "owned" table
	@JoinTable(name = "pet_store_customers",
			joinColumns = @JoinColumn(name = "customer_id"),
			inverseJoinColumns = @JoinColumn(name = "pet_store_id"))
	// Again wonder if the Class Type here matters for the linking ???
	private Set<PetStore> petStores = new HashSet<>();

}
