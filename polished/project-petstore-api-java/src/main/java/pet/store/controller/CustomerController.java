package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.model.CustomerData;
import pet.store.service.CustomerService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
     * CUSTOMER --------------------------------------------------------------------------------------
     */

    @GetMapping()
    public List<CustomerData> findCustomers(@PathVariable Long id) {
        log.info("Finding all customers for Store ID= {}", id);

        return customerService.findAllCustomersByStore(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CustomerData insertCustomer(@PathVariable Long id, @RequestBody CustomerData customerData) {
        log.info("Creating customer {}", customerData);

        return customerService.saveCustomer(customerData);
    }

    @GetMapping("/{customerId}")
    public CustomerData findCustomerById(@PathVariable Long customerId) {
        log.info("Find customer with ID={}", customerId);

        return customerService.findCustomer(customerId);
    }

    @PutMapping("/{customerId}")
    public CustomerData updateCustomer(@PathVariable Long customerId, @RequestBody CustomerData customerData) {
        customerData.setCustomerId(customerId);
        log.info("Update customer {}", customerData);

        return customerService.saveCustomer(customerData);
    }

    @DeleteMapping("/{customerId}")
    public Map<String, String> deleteCustomer(@PathVariable Long customerId) {
        log.info("Deleting customer with ID= {}", customerId);

        customerService.deleteCustomer(customerId);

        return Map.of("message", "Delete of Customer with ID= " + customerId + " was successful.");
    }
}
