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

}
