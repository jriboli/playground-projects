package pet.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.Employee;
import pet.store.entity.Store;

@Data
@NoArgsConstructor
public class EmployeeData {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String phone;
	private String jobTitle;
	@JsonIgnore
	private Store store;
	
	public EmployeeData(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.phone = employee.getPhone();
		this.jobTitle = employee.getJobTitle();
		this.store = employee.getStore();
	}	
}
