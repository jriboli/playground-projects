package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pet.store.model.EmployeeData;
import pet.store.model.StoreEmployeeData;
import pet.store.dao.EmployeeDao;
import pet.store.dao.StoreDao;
import pet.store.entity.Employee;
import pet.store.entity.Store;

@Service
@Slf4j
public class StoreService {

	private StoreDao storeDao;
	private EmployeeDao employeeDao;

	public StoreService(StoreDao storeDao, EmployeeDao employeeDao) {
		this.storeDao = storeDao;
		this.employeeDao = employeeDao;
	}

	/*
	 * STORE --------------------------------------------------------------------------------------
	 */
	
	@Transactional(readOnly = true)
	public List<StoreEmployeeData> findAllStores() {
		List<Store> stores = storeDao.findAll();
		List<StoreEmployeeData> results = new LinkedList<>();
		
		stores.forEach(store -> results.add(new StoreEmployeeData(store)));
		
		return results;		
	}

	@Transactional(readOnly = true)
	public StoreEmployeeData findStore(Long id) {
		Store store = findOrCreateStore(id);
		return new StoreEmployeeData(store);
	}
	
	@Transactional(readOnly = false)
	public StoreEmployeeData saveStore(StoreEmployeeData storeEmployeeData) {
		Long storeId = storeEmployeeData.getStoreId();
		Store store = findOrCreateStore(storeId);
		
		setFieldsInStore(store, storeEmployeeData);
		return new StoreEmployeeData(storeDao.save(store));
	}

	@Transactional(readOnly = false)
	public StoreEmployeeData updateStore(Long id, StoreEmployeeData storeEmployeeData) {
		Store store = findStoreById(id);
		
		return new StoreEmployeeData(storeDao.save(store));
	}
	
	@Transactional(readOnly = false)
	public void deleteStore(Long id) {
		// Adding this to first check that the ID is valid, or throw error
		Store store = findStoreById(id);
		storeDao.delete(store);
	}

	private void setFieldsInStore(Store store, StoreEmployeeData storeEmployeeData) {
		store.setAddress(storeEmployeeData.getAddress());
		store.setCity(storeEmployeeData.getCity());
		store.setState(storeEmployeeData.getState());
		store.setZip(storeEmployeeData.getZip());
		store.setName(storeEmployeeData.getName());
		store.setPhone(storeEmployeeData.getPhone());
	}

	private Store findOrCreateStore(Long storeId) {
		Store store;
		if(Objects.isNull(storeId)) {
			store = new Store();
		}
		else {
			store = findStoreById(storeId);
		}
		
		return store;
	}

	private Store findStoreById(Long storeId) {
		return storeDao.findById(storeId).orElseThrow(() -> new NoSuchElementException("PetStore with ID=" + storeId + " was not found."));
	}

	
	/*
	 * EMPLOYEE --------------------------------------------------------------------------------------
	 */

	public List<EmployeeData> findAllEmployeesByStore(Long storeId) {
		List<Employee> employees = employeeDao.findByStoreStoreId(storeId);
		List<EmployeeData> results = new LinkedList<>();
		
		employees.forEach(employee -> results.add(new EmployeeData(employee)));
		
		return results;		
	}
	
	public EmployeeData findEmployee(Long storeId, Long employeeId) {
		Employee employee = findOrCreateEmployee(storeId, employeeId);
		
		return new EmployeeData(employee);
	}

	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(Long storeId, EmployeeData employeeData) {
		Store store = findStoreById(storeId);
		Long employeeId = employeeData.getEmployeeId();
		Employee employee = findOrCreateEmployee(storeId, employeeId);
		
		// This is no longer needed, as we are passing the ID along in the method call
		//PetStore petStore = findPetStoreById(employeeData.getPetStore().getPetStoreId());
		employeeData.setStore(store);
		
		setFieldsInEmployee(employee, employeeData);
		return new EmployeeData(employeeDao.save(employee));		
	}

	public void deleteEmployee(Long id, Long employeeId) {
		Employee employee = findEmployeeById(id, employeeId);
		
		employeeDao.delete(employee);
	}
	
	private Employee findOrCreateEmployee(Long storeId, Long employeeId) {
		Employee employee;
		if(Objects.isNull(employeeId)) {
			employee = new Employee();
		}
		else {
			employee = findEmployeeById(storeId, employeeId);
		}
		
		return employee;
	}

	private Employee findEmployeeById(Long storeId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));
		
		if(employee.getStore().getStoreId() == storeId) {
			return employee;
		}
		else {
			throw new IllegalArgumentException("No Employee with ID= " + employeeId + " is associated Store " + storeId);
		}		
	}

	private void setFieldsInEmployee(Employee employee, EmployeeData employeeData) {
		employee.setFirstName(employeeData.getFirstName());
		employee.setLastName(employeeData.getLastName());
		employee.setJobTitle(employeeData.getJobTitle());
		employee.setPhone(employeeData.getPhone());
		employee.setStore(employeeData.getStore());
	}
}
