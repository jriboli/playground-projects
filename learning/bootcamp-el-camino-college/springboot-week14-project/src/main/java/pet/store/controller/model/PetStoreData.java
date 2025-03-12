package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

// DATA annotation will add default getters and setters
@Data
// NoArgsConstructor, will make sure this class as a no arg constructor, this is for JSON parsing with Jackson
@NoArgsConstructor
public class PetStoreData {

	private Long petStoreId;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String name;
	private String phone;
	
	private Set<EmployeeResponse> employees = new HashSet<>();
	private Set<CustomerResponse> petStoreCustomers = new HashSet<>();
	
	public PetStoreData(PetStore petStore) {
		petStoreId = petStore.getPetStoreId();
		address = petStore.getAddress();
		city = petStore.getCity();
		state = petStore.getState();
		zip = petStore.getZip();
		name = petStore.getName();
		phone = petStore.getPhone();
		
		for(Employee employee : petStore.getEmployees()) {
			employees.add(new EmployeeResponse(employee));
		}
		
		for(Customer customer : petStore.getPetStoreCustomers()) {
			petStoreCustomers.add(new CustomerResponse(customer));
		}
	}
	
	@Data
	@NoArgsConstructor
	static class EmployeeResponse {
		private Long employeeId;
		private String firstName;
		private String lastName;
		private String phone;
		private String jobTitle;
		
		public EmployeeResponse(Employee employee) {
			employeeId = employee.getEmployeeId();
			firstName = employee.getFirstName();
			lastName = employee.getLastName();
			phone = employee.getPhone();
			jobTitle = employee.getJobTitle();
		}
	}
	
	@Data
	@NoArgsConstructor
	static class CustomerResponse {
		private Long customerId;
		private String firstName;
		private String lastName;
		private String email;
		
		public CustomerResponse(Customer customer) {
			customerId = customer.getCustomerId();
			firstName = customer.getFirstName();
			lastName = customer.getLastName();
			email = customer.getEmail();
		}
	}
}
