package pet.store.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Store;

@Data
@NoArgsConstructor
public class CustomerData {
	private Long customerId;
	private String firstName;
	private String lastName;
	private String email;
	@JsonIgnore
	private Set<Store> stores;
	private Set<StoreResponse> storeList = new HashSet<>();
	
	public CustomerData(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.email = customer.getEmail();
		this.stores = customer.getStores();
		
		customer.getStores().forEach(store -> storeList.add(new StoreResponse(store)));
	}
	
	@Data
	@NoArgsConstructor
	static class StoreResponse {
		private Long storeId;
		private String address;
		private String city;
		private String state;
		private String zip;
		private String name;
		private String phone;
		
		public StoreResponse(Store store) {
			this.storeId = store.getStoreId();
			this.address = store.getAddress();
			this.city = store.getCity();
			this.state = store.getState();
			this.zip = store.getZip();
			this.name = store.getName();
			this.phone = store.getPhone();
		}
	}
}
