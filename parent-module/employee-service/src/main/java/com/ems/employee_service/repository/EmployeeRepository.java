package com.ems.employee_service.repository;

import com.ems.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {


    List<Employee> findByDeptId(Long deptId);

    //query for all employee under 2k
    @Query("SELECT e FROM Employee e WHERE e.salary < 2000")
    List<Employee> findAllEmployeeUnder2k();

//    query for all employees between 2k and 5k salary
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN 2000 AND 5000")
    List<Employee> findAllEmployeeBetween2kAnd5k();

//    query for all employees above 5k salary
    @Query("SELECT e FROM Employee e WHERE e.salary > 5000")
    List<Employee> findAllEmployeeAbove5k();
}
