package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(readOnly = false)
	public PetStoreData saveStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		setFieldsInPetStore(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
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

	public PetStoreData updateStore(Long id, PetStoreData petStoreData) {
		PetStore petStore = findPetStoreById(id);
		
		return new PetStoreData(petStoreDao.save(petStore));
	}

	public List<EmployeeData> findAllEmployeesByStore(Long petStoreId) {
		List<Employee> employees = employeeDao.findByPetStorePetStoreId(petStoreId);
		List<EmployeeData> results = new LinkedList<>();
		
		employees.forEach(employee -> results.add(new EmployeeData(employee)));
		
		return results;		
	}

	private Employee findEmployeeById(Long employeeId) {
		return employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
	}
	
	private Employee findOrCreateEmployee(Long employeeId) {
		Employee employee;
		if(Objects.isNull(employeeId)) {
			employee = new Employee();
		}
		else {
			employee = findEmployeeById(employeeId);
		}
		
		return employee;
	}
	
	public EmployeeData findEmployee(Long id, Long employeeId) {
		Employee employee = findOrCreateEmployee(employeeId);
		
		if(employee.getPetStore().getPetStoreId() != id) {
			return null;
		}
		
		return new EmployeeData(employee);
	}

	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(EmployeeData employeeData) {
		Long employeeId = employeeData.getEmployeeId();
		Employee employee = findOrCreateEmployee(employeeId);
		
		PetStore petStore = findPetStoreById(employeeData.getPetStore().getPetStoreId());
		employeeData.setPetStore(petStore);
		
		setFieldsInEmployee(employee, employeeData);
		return new EmployeeData(employeeDao.save(employee));		
	}

	private void setFieldsInEmployee(Employee employee, EmployeeData employeeData) {
		employee.setFirstName(employeeData.getFirstName());
		employee.setLastName(employeeData.getLastName());
		employee.setJobTitle(employeeData.getJobTitle());
		employee.setPhone(employeeData.getPhone());
		employee.setPetStore(employeeData.getPetStore());		
	}

	public List<CustomerData> findAllCustomersByStore(Long id) {
		// Name details are notred in the CustomerDao class
		List<Customer> customers = customerDao.findPetStoreCustomersByPetStores_PetStoreId(id);
		List<CustomerData> results = new LinkedList<>();
		
		customers.forEach(customer -> results.add(new CustomerData(customer)));
		
		return results;		
	}

	public CustomerData findCustomer(Long id, Long customerId) {
		Customer customer = findOrCreateCustomer(customerId);
		
		// Do we need to filter on Store - ??? 
		// Skipping the filter for now
		
		return new CustomerData(customer);
	}

	private Customer findOrCreateCustomer(Long customerId) {
		Customer customer;
		if(Objects.isNull(customerId)) {
			customer = new Customer();
		}
		else {
			customer = findCustomerById(customerId);
		}

		return customer;
	}

	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
	}
	
	

}
