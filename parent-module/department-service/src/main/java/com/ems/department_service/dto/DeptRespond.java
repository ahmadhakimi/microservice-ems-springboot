package com.ems.department_service.dto;

import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeptRespond {

    private Long deptId;
    private String deptName;
    private String deptAddress;
    private String deptCode;

    private List<String> employeeNames;

}
