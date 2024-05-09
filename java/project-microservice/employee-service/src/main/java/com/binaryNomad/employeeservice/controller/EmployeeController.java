package com.binaryNomad.employeeservice.controller;

import com.binaryNomad.employeeservice.model.Employee;
import com.binaryNomad.employeeservice.repository.EmployeeRepository;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeController {

    private EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        log.info("Employee add: {}", employee);
        return ResponseEntity.ok(repository.add(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        log.info("Employee find all");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<Employee> findById(@PathVariable Long employee_id) {
        log.info("Employee find: id={}", employee_id);
        return ResponseEntity.ok(repository.findById(employee_id));
    }

    @GetMapping("/departments/{department_id}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable Long department_id) {
        log.info("Employee find: department_id={}", department_id);
        return ResponseEntity.ok(repository.findByDepartment(department_id));
    }


}
