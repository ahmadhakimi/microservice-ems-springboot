package com.ems.employee_service.service;

import com.ems.employee_service.dto.DeptRespond;
import com.ems.employee_service.dto.EmployeeInDept;
import com.ems.employee_service.dto.EmployeeRequest;
import com.ems.employee_service.dto.EmployeeRespond;
import com.ems.employee_service.entity.Employee;
import com.ems.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient.Builder webClientBuilder;

    //create new employee
    public EmployeeRespond createNewEmployee (EmployeeRequest employeeRequest) {

        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
                .password(employeeRequest.getPassword())
                .role(employeeRequest.getRole())
                .createdBy(employeeRequest.getCreatedBy())
                .updatedBy(employeeRequest.getUpdatedBy())
                .bonus(employeeRequest.getBonus())
                .salary(employeeRequest.getSalary())
                .annualLeave(employeeRequest.getAnnualLeave())
                .totalClaim(employeeRequest.getTotalClaim())
                .deptId(employeeRequest.getDeptId())
                .build();

        Employee saveEmployee = employeeRepository.save(employee);

        return mapToEmployeeEntity(saveEmployee);
    }

//    get all list of employee exist
    public List<EmployeeRespond> getAllEmployee () {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    DeptRespond department = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8080/api/dept/" + employee.getDeptId())
                            .retrieve()
                            .bodyToMono(DeptRespond.class)
                            .block();

                    return mapToEmployeeEntity(employee, department);
                })
                .collect(Collectors.toList());
    }

//    view employee by id

    public EmployeeRespond getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No employee id: " + id));

        DeptRespond department = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/dept/" + employee.getDeptId())
                .retrieve()
                .bodyToMono(DeptRespond.class)
                .block();

        return mapToEmployeeEntity(employee, department);
    }

//    update employee by id

    public EmployeeRespond updateEmployee(String id, EmployeeRequest employeeRequest) {
//        Check existing employee
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee id: " + id + " is not exist"));

        if (employeeRequest.getName() != null) {
            existingEmployee.setName(employeeRequest.getName());
        }

        if(employeeRequest.getRole() != null) {
            existingEmployee.setRole(employeeRequest.getRole());
        }

        if(employeeRequest.getEmail() != null) {
            existingEmployee.setEmail(employeeRequest.getEmail());
        }

        if(employeeRequest.getPassword() != null) {
            existingEmployee.setPassword(employeeRequest.getPassword());
        }

        if(employeeRequest.getUpdatedBy() != null) {
            existingEmployee.setUpdatedBy(employeeRequest.getUpdatedBy());
        }

        if(employeeRequest.getCreatedBy() != null) {
            existingEmployee.setCreatedBy(employeeRequest.getCreatedBy());
        }

        if(employeeRequest.getTotalClaim() != existingEmployee.getTotalClaim()) {
            existingEmployee.setTotalClaim(employeeRequest.getTotalClaim());
        }

        if(employeeRequest.getAnnualLeave() != existingEmployee.getAnnualLeave()) {
            existingEmployee.setAnnualLeave(employeeRequest.getAnnualLeave());
        }

        if(employeeRequest.getBonus() != existingEmployee.getBonus()) {
            existingEmployee.setBonus(employeeRequest.getBonus());
        }

        if(employeeRequest.getSalary() != existingEmployee.getSalary()) {
            existingEmployee.setSalary(employeeRequest.getSalary());
        }

        if(employeeRequest.getDeptId() != null) {
            existingEmployee.setDeptId(employeeRequest.getDeptId());
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        DeptRespond department = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/dept/" + existingEmployee.getDeptId())
                .retrieve()
                .bodyToMono(DeptRespond.class)
                .block();

        return mapToEmployeeEntity(updatedEmployee, department);

    }

//    delete employee by id
    public void deleteEmployee(String id) {

//        Find employee exist or not
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("emp id : " + id + " not found"));

//        Change isDeleted to true

        employee.setDeleted(true);

//        save into repository
        employeeRepository.save(employee);
    }



//    method to map from dto into entity
    private EmployeeRespond mapToEmployeeEntity (Employee employee) {
        return EmployeeRespond.builder()
                .empId(employee.getEmpId())
                .name(employee.getName())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .role(employee.getRole())
                .createdBy(employee.getCreatedBy())
                .updatedBy(employee.getUpdatedBy())
                .deleted(employee.isDeleted())
                .bonus(employee.getBonus())
                .salary(employee.getSalary())
                .annualLeave(employee.getAnnualLeave())
                .totalClaim(employee.getTotalClaim())
                .build();
    }

    private EmployeeRespond mapToEmployeeEntity (Employee employee, DeptRespond department) {
        return EmployeeRespond.builder()
                .empId(employee.getEmpId())
                .name(employee.getName())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .role(employee.getRole())
                .createdBy(employee.getCreatedBy())
                .updatedBy(employee.getUpdatedBy())
                .deleted(employee.isDeleted())
                .bonus(employee.getBonus())
                .salary(employee.getSalary())
                .annualLeave(employee.getAnnualLeave())
                .totalClaim(employee.getTotalClaim())
                .department(department)
                .build();

    }

    public List<EmployeeInDept> getEmployeesByDeptId(Long deptId) {
        return employeeRepository.findByDeptId(deptId) // Changed to findByDeptId
                .stream()
                .map(employee -> EmployeeInDept.builder().name(employee.getName()).build())
                .collect(Collectors.toList());
    }
}
