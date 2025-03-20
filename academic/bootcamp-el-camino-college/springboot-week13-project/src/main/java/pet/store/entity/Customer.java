package pet.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	// This MappedBy needed to match the ManyToMany in PetStore class - WHY ???
	@ManyToMany(mappedBy = "petStoreCustomers")
	private Set<PetStore> petStores = new HashSet<>();

}
