package com.ems.employee_service.dto;

import com.ems.employee_service.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
    private String createdBy;
    private String updatedBy;

    private Long deptId;

    private double bonus;
    private double salary;
    private int annualLeave;
    private double totalClaim;






}
