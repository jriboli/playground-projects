package com.binaryNomad.departmentservice.controller;

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

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
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

    @PostMapping
    public ResponseEntity<Department> add(@RequestBody Department department) {
        log.info("Department add: {}", department);
        return ResponseEntity.ok(repository.addDepartment(department));
    }
}
