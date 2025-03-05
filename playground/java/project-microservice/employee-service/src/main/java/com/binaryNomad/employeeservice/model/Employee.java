package com.binaryNomad.employeeservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Long id;
    private Long departmentId;
    private String name;
    private int age;
    private String position;


}
