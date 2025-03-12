package pet.store.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.CustomerData;
import pet.store.controller.model.EmployeeData;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
@Slf4j
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	// Note folks have DAO and Repository interchangeably 
	// NEED to look into this annotation some more - ???
	// Use dependency injection and move away from this annotation
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;


	/*
	 * PET STORE --------------------------------------------------------------------------------------
	 */
	
	@Transactional(readOnly = true)
	public List<PetStoreData> findAllStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		List<PetStoreData> results = new LinkedList<>();
		
		petStores.forEach(petStore -> results.add(new PetStoreData(petStore)));
		
		return results;		
	}

	@Transactional(readOnly = true)
	public PetStoreData findStore(Long id) {
		PetStore petStore = findOrCreatePetStore(id);
		return new PetStoreData(petStore);
	}
	
	@Transactional(readOnly = false)
	public PetStoreData saveStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		setFieldsInPetStore(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	@Transactional(readOnly = false)
	public PetStoreData updateStore(Long id, PetStoreData petStoreData) {
		PetStore petStore = findPetStoreById(id);
		
		return new PetStoreData(petStoreDao.save(petStore));
	}
	
	@Transactional(readOnly = false)
	public void deleteStore(Long id) {
		// Adding this to first check that the ID is valid, or throw error
		PetStore petStore = findPetStoreById(id);
		petStoreDao.delete(petStore);	
	}

	private void setFieldsInPetStore(PetStore petStore, PetStoreData petStoreData) {
		petStore.setAddress(petStoreData.getAddress());
		petStore.setCity(petStoreData.getCity());
		petStore.setState(petStoreData.getState());
		petStore.setZip(petStoreData.getZip());
		petStore.setName(petStoreData.getName());
		petStore.setPhone(petStoreData.getPhone());		
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;
		if(Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		}
		else {
			petStore = findPetStoreById(petStoreId);
		}
		
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("PetStore with ID=" + petStoreId + " was not found."));
	}

	
	/*
	 * EMPLOYEE --------------------------------------------------------------------------------------
	 */

	public List<EmployeeData> findAllEmployeesByStore(Long petStoreId) {
		List<Employee> employees = employeeDao.findByPetStorePetStoreId(petStoreId);
		List<EmployeeData> results = new LinkedList<>();
		
		employees.forEach(employee -> results.add(new EmployeeData(employee)));
		
		return results;		
	}
	
	public EmployeeData findEmployee(Long id, Long employeeId) {
		Employee employee = findOrCreateEmployee(id, employeeId);
		
		return new EmployeeData(employee);
	}

	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(Long id, EmployeeData employeeData) {
		PetStore petStore = findPetStoreById(id);
		Long employeeId = employeeData.getEmployeeId();
		Employee employee = findOrCreateEmployee(id, employeeId);
		
		// This is no longer needed, as we are passing the ID along in the method call
		//PetStore petStore = findPetStoreById(employeeData.getPetStore().getPetStoreId());
		employeeData.setPetStore(petStore);
		
		setFieldsInEmployee(employee, employeeData);
		return new EmployeeData(employeeDao.save(employee));		
	}

	public void deleteEmployee(Long id, Long employeeId) {
		Employee employee = findEmployeeById(id, employeeId);
		
		employeeDao.delete(employee);
	}
	
	private Employee findOrCreateEmployee(Long id, Long employeeId) {
		Employee employee;
		if(Objects.isNull(employeeId)) {
			employee = new Employee();
		}
		else {
			employee = findEmployeeById(id, employeeId);
		}
		
		return employee;
	}

	private Employee findEmployeeById(Long id, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
		
		if(employee.getPetStore().getPetStoreId() == id) {
			return employee;
		}
		else {
			throw new IllegalArgumentException("No Employee with ID= " + employeeId + " is associated Store " + id);
		}		
	}

	private void setFieldsInEmployee(Employee employee, EmployeeData employeeData) {
		employee.setFirstName(employeeData.getFirstName());
		employee.setLastName(employeeData.getLastName());
		employee.setJobTitle(employeeData.getJobTitle());
		employee.setPhone(employeeData.getPhone());
		employee.setPetStore(employeeData.getPetStore());		
	}
	

	/*
	 * CUSTOMER --------------------------------------------------------------------------------------
	 */

	public List<CustomerData> findAllCustomersByStore(Long id) {
		// Name details are noted in the CustomerDao class
		List<Customer> customers = customerDao.findPetStoreCustomersByPetStores_PetStoreId(id);
		List<CustomerData> results = new LinkedList<>();
		
		customers.forEach(customer -> results.add(new CustomerData(customer)));
		
		return results;		
	}

	public CustomerData findCustomer(Long id, Long customerId) {
		Customer customer = findOrCreateCustomer(id, customerId);		
		return new CustomerData(customer);
	}

	public CustomerData saveCustomer(Long id, CustomerData customerData) {
		PetStore petStore = findPetStoreById(id);
		Long customerId = customerData.getCustomerId();
		Customer customer = findOrCreateCustomer(id, customerId);
		
		Set<PetStore> petStores = new HashSet<>();
		if(Objects.isNull(customerData.getPetStores())) {
			petStores.add(petStore);
		}
		else {
			petStores = customerData.getPetStores();
			boolean hasCustomerStoreAssociation = petStores
					.stream()
					.anyMatch(petStore2 -> petStore2.getPetStoreId() == id);
			
			if(!hasCustomerStoreAssociation) {
				petStores.add(petStore);
			}
		}
		
		customerData.setPetStores(petStores);
		
		setFieldsInCustomer(customer, customerData);
//		log.info("Logging the customerData obj == {}", customerData);
//		log.info("Logging the customer obj == {}", customer);
//		
//		log.info("Trying the GET method == {}", customerData.getPetStores());
		
		for(PetStore ps : petStores) {
			ps.getPetStoreCustomers().add(customer);
		}
		
		return new CustomerData(customerDao.save(customer));
	}

	public void deleteCustomer(Long id, Long customerId) {
		Customer customer = findCustomerById(id, customerId);
		
		// Remove the customer from each associated store
		for(PetStore ps : customer.getPetStores()) {
			ps.getPetStoreCustomers().remove(customer);
		}
		
		customerDao.delete(customer);
	}
	
	private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
		customer.setCustomerId(customerData.getCustomerId());
		customer.setFirstName(customerData.getFirstName());
		customer.setLastName(customerData.getLastName());
		customer.setEmail(customerData.getEmail());
		customer.setPetStores(customerData.getPetStores());		
	}

	private Customer findOrCreateCustomer(Long id, Long customerId) {
		Customer customer;
		if(Objects.isNull(customerId)) {
			customer = new Customer();
		}
		else {
			customer = findCustomerById(id, customerId);
		}

		return customer;
	}

	private Customer findCustomerById(Long id, Long customerId) {
		Customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
		
		Set<PetStore> petStores = customer.getPetStores();
		
		boolean hasPetStoreWithId = petStores
				.stream()
				.anyMatch(petStore -> petStore.getPetStoreId() == id);
		
		if(hasPetStoreWithId) {
			return customer;
		}
		else {
			throw new IllegalArgumentException("No Customer with ID= " + customerId + " is associated Store " + id);
		}
	}

	


	
	

}
