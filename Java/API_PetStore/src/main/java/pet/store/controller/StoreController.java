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
import pet.store.model.CustomerData;
import pet.store.model.EmployeeData;
import pet.store.model.InventoryData;
import pet.store.model.StoreEmployeeData;
import pet.store.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@Slf4j
public class StoreController {
	// NEED to look into this annotation some more - ???
	// Use dependency injection and move away from this annotation
	@Autowired
	private StoreService storeService;

	/*
	 * STORE --------------------------------------------------------------------------------------
	 */
	
	@GetMapping()
	public List<StoreEmployeeData> findAllStores(){
		log.info("Finding all Pet Stores");
		
		return storeService.findAllStores();
	}
	
	// Matching the parameter name to the Method @PathVariable
	// Else need to declare with @PathVariable(name = blah)
	@GetMapping("/{id}")
	// NEED to understand why setting ResponseStatus here verses somewhere else - ???
	@ResponseStatus(code = HttpStatus.OK)
	public StoreEmployeeData findStore(@PathVariable Long id) {
		log.info("Find store {}", id);
		
		return storeService.findStore(id);
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public StoreEmployeeData insertStore(@RequestBody StoreEmployeeData storeEmployeeData) {
		log.info("Create store {}", storeEmployeeData);
		
		return storeService.saveStore(storeEmployeeData);
	}	
	
	@PutMapping("/{id}")
	public StoreEmployeeData updateStore(@PathVariable Long id, @RequestBody StoreEmployeeData storeEmployeeData) {
		log.info("Updating store {} with {}", id, storeEmployeeData);
		
		return storeService.updateStore(id, storeEmployeeData);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, String> deleteStore(@PathVariable Long id) {
		log.info("Deleting Store with ID={}", id);
		
		storeService.deleteStore(id);
		
		return Map.of("message", "Deletion of Store of ID=" + id + " was successful.");
	}

	/*
	 * EMPLOYEE --------------------------------------------------------------------------------------
	 */
	
	@GetMapping("/{id}/employees")
	public List<EmployeeData> findEmployees(@PathVariable Long id) {
		log.info("Find employees for store {}", id);
		
		return storeService.findAllEmployeesByStore(id);
	}

	@PostMapping("/{id}/employees")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData insertEmployee(@PathVariable Long id, @RequestBody EmployeeData employeeData) {
		// This were breaking the rule, we should not access the Data layer directly
		// We should be going through the Service layer - Now Removed
		//employeeData.setPetStore(new PetStore());
		//employeeData.getPetStore().setPetStoreId(id);
		log.info("Create Employee {}", employeeData);

		return storeService.saveEmployee(id, employeeData);
	}
	
	@GetMapping("/{id}/employees/{employeeId}")
	public EmployeeData findEmployeeById(@PathVariable Long id, @PathVariable Long employeeId) {
		log.info("Find employee with ID={}", employeeId);
		
		return storeService.findEmployee(id, employeeId);
	}
	
	@PutMapping("/{id}/employees/{employeeId}")
	public EmployeeData updateEmployee(@PathVariable Long id, @PathVariable Long employeeId, @RequestBody EmployeeData employeeData) {
		employeeData.setEmployeeId(employeeId);
		log.info("Update Employee {}", employeeData);
		
		return storeService.saveEmployee(id, employeeData);
	}
	
	@DeleteMapping("/{id}/employees/{employeeId}")
	public Map<String, String> deleteEmployee(@PathVariable Long id, @PathVariable Long employeeId) {
		log.info("Deleting employee with ID= {}", employeeId);
		
		storeService.deleteEmployee(id, employeeId);
		
		return Map.of("message", "Deletion of Employee with ID= " + employeeId + " was successful.");
	}

	// WEEK 13 Class notes
	// RequestMapping cannot be resolved to a type
	// @RequestMapping(value = "/random", method = RequestMethod.GET)
	// Looks like we can specify RequestMapping at the method level or the class level
	
	// @GetMapping(value = "/random/25")
	// 
}
