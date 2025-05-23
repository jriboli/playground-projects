package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.CustomerData;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet-store")
@Slf4j
public class PetStoreController {
	// NEED to look into this annotation some more - ???
	// Use dependency injection and move away from this annotation
	@Autowired
	private PetStoreService petStoreService;

	/*
	 * PET STORE --------------------------------------------------------------------------------------
	 */
	
	@GetMapping("/store")
	public List<PetStoreData> findAllStores(){
		log.info("Finding all Pet Stores");
		
		return petStoreService.findAllStores();
	}
	
	// Matching the parameter name to the Method @PathVariable
	// Else need to declare with @PathVariable(name = blah)
	@GetMapping("/store/{id}")
	// NEED to understand why setting ResponseStatus here verses somewhere else - ???
	@ResponseStatus(code = HttpStatus.OK)
	public PetStoreData findStore(@PathVariable Long id) {
		log.info("Find store {}", id);
		
		return petStoreService.findStore(id);
	}
	
	@PostMapping("/store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertStore(@RequestBody PetStoreData petStoreData) {
		log.info("Create store {}", petStoreData);
		
		return petStoreService.saveStore(petStoreData);
	}	
	
	@PutMapping("/store/{id}")
	public PetStoreData updateStore(@PathVariable Long id, @RequestBody PetStoreData petStoreData) {
		log.info("Updating store {} with {}", id, petStoreData);
		
		return petStoreService.updateStore(id, petStoreData);
	}
	
	@DeleteMapping("/store/{id}")
	public Map<String, String> deleteStore(@PathVariable Long id) {
		log.info("Deleting Store with ID={}", id);
		
		petStoreService.deleteStore(id);
		
		return Map.of("message", "Deletion of Store of ID=" + id + " was successful.");
	}
	

	/*
	 * EMPLOYEE --------------------------------------------------------------------------------------
	 */
	
	@GetMapping("/store/{id}/employee")
	public List<EmployeeData> findEmployees(@PathVariable Long id) {
		log.info("Find employees for store {}", id);
		
		return petStoreService.findAllEmployeesByStore(id);
	}
	
	@GetMapping("/store/{id}/employee/{employeeId}")
	public EmployeeData findEmployeeById(@PathVariable Long id, @PathVariable Long employeeId) {
		log.info("Find employee with ID={}", employeeId);
		
		return petStoreService.findEmployee(id, employeeId);
	}
	
	@PostMapping("/store/{id}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData insertEmployee(@PathVariable Long id, @RequestBody EmployeeData employeeData) {
		// This were breaking the rule, we should not access the Data layer directly
		// We should be going through the Service layer - Now Removed
		//employeeData.setPetStore(new PetStore());
		//employeeData.getPetStore().setPetStoreId(id);
		log.info("Create Employee {}", employeeData);
		
		return petStoreService.saveEmployee(id, employeeData);
	}
	
	@PutMapping("/store/{id}/employee/{employeeId}")
	public EmployeeData updateEmployee(@PathVariable Long id, @PathVariable Long employeeId, @RequestBody EmployeeData employeeData) {
		employeeData.setEmployeeId(employeeId);
		log.info("Update Employee {}", employeeData);
		
		return petStoreService.saveEmployee(id, employeeData);
	}
	
	@DeleteMapping("/store/{id}/employee/{employeeId}")
	public Map<String, String> deleteEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
		log.info("Deleting employee with ID= {}", employeeId);
		
		petStoreService.deleteEmployee(id, employeeId);
		
		return Map.of("message", "Deletion of Employee with ID= " + employeeId + " was successful.");
	}

	/*
	 * CUSTOMER --------------------------------------------------------------------------------------
	 */
	
	@GetMapping("/store/{id}/customer")
	public List<CustomerData> findCustomers(@PathVariable Long id) {
		log.info("Finding all customers for Store ID= {}", id);
		
		return petStoreService.findAllCustomersByStore(id);
	}
	
	@GetMapping("/store/{id}/customer/{customerId}")
	public CustomerData findCustomerById(@PathVariable Long id, @PathVariable Long customerId) {
		log.info("Find customer with ID={}, for Store {}", customerId, id);
		
		return petStoreService.findCustomer(id, customerId);
	}
	
	@PostMapping("/store/{id}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insertCustomer(@PathVariable Long id, @RequestBody CustomerData customerData) {
		log.info("Creating customer {}", customerData);
		
		return petStoreService.saveCustomer(id, customerData);
	}
	
	@PutMapping("/store/{id}/customer/{customerId}")
	public CustomerData updateCustomer(@PathVariable Long id, @PathVariable Long customerId, @RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Update customer {}", customerData);
		
		return petStoreService.saveCustomer(id, customerData);
	}
	
	@DeleteMapping("/store/{id}/customer/{customerId}")
	public Map<String, String> deleteCustomer(@PathVariable Long id, @PathVariable Long customerId) {
		log.info("Deleting customer with ID= {}", customerId);
		
		petStoreService.deleteCustomer(id, customerId);
		
		return Map.of("message", "Delete of Customer with ID= " + customerId + " was successful.");
	}
	
	
	
	// WEEK 13 Class notes
	// RequestMapping cannot be resolved to a type
	// @RequestMapping(value = "/random", method = RequestMethod.GET)
	// Looks like we can specify RequestMapping at the method level or the class level
	
	// @GetMapping(value = "/random/25")
	// 
}
