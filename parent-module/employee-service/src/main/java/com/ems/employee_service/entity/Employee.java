package com.ems.employee_service.entity;

import com.ems.employee_service.enumerate.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "ems_emp_t")
@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String empId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String createdBy;
    private String updatedBy;
    private boolean deleted;

    private Long deptId; //Reference to Dept ID

    private double bonus;
    private double salary;
    private int annualLeave;
    private double totalClaim;


}
