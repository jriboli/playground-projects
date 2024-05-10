package com.binaryNomad.departmentservice.client;

import com.binaryNomad.departmentservice.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("/api/v1/employees/departments/{department_id}")
    public List<Employee> findByDepartment(@PathVariable Long department_id);
}
