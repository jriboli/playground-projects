package pet.store.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.store.dao.CustomerDao;
import pet.store.entity.Customer;
import pet.store.entity.Store;
import pet.store.model.CustomerData;

import java.util.*;

@Service
@Slf4j
public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /*
     * CUSTOMER --------------------------------------------------------------------------------------
     */

    public List<CustomerData> findAllCustomersByStore(Long id) {
        // Name details are noted in the CustomerDao class
        List<Customer> customers = customerDao.findStoreCustomersByStores_StoreId(id);
        List<CustomerData> results = new LinkedList<>();
        customers.forEach(customer -> results.add(new CustomerData(customer)));

        return results;
    }

    public CustomerData findCustomer(Long customerId) {
        Customer customer = findOrCreateCustomer(customerId);
        return new CustomerData(customer);
    }

    public CustomerData saveCustomer(CustomerData customerData) {
        Long customerId = customerData.getCustomerId();
        Customer customer = findOrCreateCustomer(customerId);

        setFieldsInCustomer(customer, customerData);
        return new CustomerData(customerDao.save(customer));
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = findCustomerById(customerId);

        // Remove the customer from each associated store
        for(Store ps : customer.getStores()) {
            ps.getCustomers().remove(customer);
        }

        customerDao.delete(customer);
    }

    private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
        customer.setCustomerId(customerData.getCustomerId());
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        customer.setStores(customerData.getStores());
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
