package com.ems.department_service.controller;

import com.ems.department_service.dto.DeptRequest;
import com.ems.department_service.dto.DeptRespond;
import com.ems.department_service.dto.EmployeeRespond;
import com.ems.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/dept")

public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DeptRespond> createNewDept (@RequestBody DeptRequest deptRequest) {
        DeptRespond newDept = departmentService.createNewDept(deptRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDept);

    }

    @GetMapping
    public ResponseEntity<List<DeptRespond>> getAllDept () {
        List<DeptRespond> listDept = departmentService.listOfAllDept();
        return ResponseEntity.status(HttpStatus.OK).body(listDept);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<DeptRespond> getDeptById (@PathVariable Long id) {
        DeptRespond deptById = departmentService.getDeptById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deptById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeptRespond> updateDept (@PathVariable Long id, @RequestBody DeptRequest request) {
        DeptRespond updatedDept = departmentService.updateDept(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDept);
    }

    @DeleteMapping("/{id}")
    public String deleteDept (@PathVariable Long id){
        departmentService.deleteDept(id);
        String s = "Department " + id + " Successfully deleted";
        return s;
    }


}
