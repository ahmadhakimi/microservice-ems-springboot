package com.ems.employee_service.controller;

import com.ems.employee_service.dto.EmployeeInDept;
import com.ems.employee_service.dto.EmployeeRequest;
import com.ems.employee_service.dto.EmployeeRespond;
import com.ems.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")

public class EmployeeController {

    private final EmployeeService employeeService;

    //route create employee
    @PostMapping()
    public ResponseEntity<EmployeeRespond> createNewEmployee (@RequestBody EmployeeRequest employeeRequest) {
        EmployeeRespond newEmployee = employeeService.createNewEmployee(employeeRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

//    route get all employee

    @GetMapping()
    public ResponseEntity<List<EmployeeRespond>> getAllEmployee() {
        List<EmployeeRespond> allEmployee = employeeService.getAllEmployee();

        return ResponseEntity.status(HttpStatus.FOUND).body(allEmployee);
    }

//    route get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRespond> getEmpById(@PathVariable String id){
        EmployeeRespond empById = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(empById);
    }

//    route update employee by id

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRespond> updateEmpById(@PathVariable String id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeRespond updateEmployee = employeeService.updateEmployee(id, employeeRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }

//    route soft delete employee
    @DeleteMapping("/{id}")
    public String  deletedEmployee (@PathVariable String id) {
        employeeService.deleteEmployee(id);
        String s = "The employee :" + id + " is successfully deleted";
        return s;

    }

    //mapping webclient from get department by id
    @GetMapping("/dept/{deptId}")
    public ResponseEntity<List<EmployeeInDept>> getEmployeesByDeptId(@PathVariable Long deptId) {
        List<EmployeeInDept> employees = employeeService.getEmployeesByDeptId(deptId);
        return ResponseEntity.ok(employees);
    }


}
