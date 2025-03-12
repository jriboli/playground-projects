package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.PetStore;

@Data
@NoArgsConstructor
public class CustomerData {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private Set<PetStoreResponse> petStores = new HashSet<>();
	
	public CustomerData(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
		
		customer.getPetStores().forEach(petStore -> petStores.add(new PetStoreResponse(petStore)));
	}
	
	@Data
	@NoArgsConstructor
	static class PetStoreResponse {
		private Long petStoreId;
		private String address;
		private String city;
		private String state;
		private String zip;
		private String name;
		private String phone;
		
		public PetStoreResponse(PetStore petStore) {
			this.petStoreId = petStore.getPetStoreId();
			this.address = petStore.getAddress();
			this.city = petStore.getCity();
			this.state = petStore.getState();
			this.zip = petStore.getZip();
			this.name = petStore.getName();
			this.phone = petStore.getPhone();
		}
	}
}
