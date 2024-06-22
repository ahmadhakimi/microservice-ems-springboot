package com.ems.department_service.dto;

import com.ems.department_service.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeRespond {

    private String empId;
    private String name;
//    private String email;
//    private String password;
//    private Role role;
//    private String createdBy;
//    private String updatedBy;
//    private boolean deleted;
//
//    private double bonus;
//    private double salary;
//    private int annualLeave;
//    private double totalClaim;

}
