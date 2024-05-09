package com.binaryNomad.departmentservice.controller;

import com.binaryNomad.departmentservice.client.EmployeeClient;
import com.binaryNomad.departmentservice.model.Department;
import com.binaryNomad.departmentservice.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@Slf4j
public class DepartmentController {

    private DepartmentRepository repository;
    private EmployeeClient employeeClient;

    public DepartmentController(DepartmentRepository repository, EmployeeClient employeeClient) {
        this.repository = repository;
        this.employeeClient = employeeClient;
    }

    @GetMapping("/{department_id}")
    public ResponseEntity<Department> getById(@PathVariable Long department_id) {
        log.info("Department find: id={}", department_id);
        return ResponseEntity.ok(repository.findById(department_id));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        log.info("Department find all");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/with-employees")
    public ResponseEntity<List<Department>> findAllWithEmployees() {
        log.info("Department find: All with employees");
        List<Department> departments = repository.findAll();

        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));

        return ResponseEntity.ok(departments);
    }

    @PostMapping
    public ResponseEntity<Department> add(@RequestBody Department department) {
        log.info("Department add: {}", department);
        return ResponseEntity.ok(repository.addDepartment(department));
    }
}
