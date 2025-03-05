package pet.store.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.Store;

// DATA annotation will add default getters and setters
@Data
// NoArgsConstructor, will make sure this class as a no arg constructor, this is for JSON parsing with Jackson
@NoArgsConstructor
public class StoreEmployeeData {

	private Long storeId;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String name;
	private String phone;
	
	private Set<EmployeeResponse> employees = new HashSet<>();
	private Set<CustomerResponse> customers = new HashSet<>();
	
	public StoreEmployeeData(Store store) {
		storeId = store.getStoreId();
		address = store.getAddress();
		city = store.getCity();
		state = store.getState();
		zip = store.getZip();
		name = store.getName();
		phone = store.getPhone();
		
		for(Employee employee : store.getEmployees()) {
			employees.add(new EmployeeResponse(employee));
		}
		
		for(Customer customer : store.getCustomers()) {
			customers.add(new CustomerResponse(customer));
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
