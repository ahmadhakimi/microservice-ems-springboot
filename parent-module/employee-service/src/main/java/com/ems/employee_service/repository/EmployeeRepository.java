package com.ems.employee_service.repository;

import com.ems.employee_service.entity.Employee;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {


    List<Employee> findByDeptId(Long deptId);

}
