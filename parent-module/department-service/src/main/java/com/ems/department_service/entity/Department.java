package com.ems.department_service.entity;

import com.ems.department_service.dto.EmployeeRespond;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ems_dept_t")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq")
    @SequenceGenerator(name = "department_seq", sequenceName = "department_seq", initialValue = 100, allocationSize = 1)
    private Long deptId;
    private String deptName;
    private String deptAddress;
    private String deptCode;

}
