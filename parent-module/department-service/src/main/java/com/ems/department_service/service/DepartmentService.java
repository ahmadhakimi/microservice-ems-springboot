package com.ems.department_service.service;

import com.ems.department_service.dto.DeptRequest;
import com.ems.department_service.dto.DeptRespond;
import com.ems.department_service.dto.EmployeeRespond;
import com.ems.department_service.entity.Department;
import com.ems.department_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final WebClient.Builder webClientBuilder;


    public DeptRespond createNewDept (DeptRequest deptRequest) {
        Department department = Department.builder()
                .deptName(deptRequest.getDeptName())
                .deptAddress(deptRequest.getDeptAddress())
                .deptCode(deptRequest.getDeptCode())
                .build();

        Department saveDept = departmentRepository.save(department);

        return mapToDeptEntity(saveDept);
    }

    public List<DeptRespond> listOfAllDept () {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToDeptEntity)
                .collect(Collectors.toList());
    }

    //get dept by id

    public DeptRespond getDeptById(Long id) {
        //retrieve dept from jpa
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No dept id: " + id));

        List<String> employeeNames = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/api/employee/dept/" + id)
                .retrieve()
                .bodyToFlux(EmployeeRespond.class)
                .map(EmployeeRespond::getName)
                .collectList()
                .block();
        return mapToDeptEntity(department, employeeNames);
    }

    public DeptRespond updateDept (Long deptId, DeptRequest deptRequest) {
        //check if the dept is existed or not
        Department department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new NoSuchElementException("No Dept ID: " + deptId + " available"));

        //update the dept using setter method
        if(deptRequest.getDeptName() != null) {

            department.setDeptName(deptRequest.getDeptName());
        }

        if(deptRequest.getDeptAddress() != null) {

            department.setDeptAddress(deptRequest.getDeptAddress());
        }

        if(deptRequest.getDeptCode() != null) {

            department.setDeptCode(deptRequest.getDeptCode());
        }

        Department updateDept = departmentRepository.save(department);

        return mapToDeptEntity(updateDept);

    }

    public void deleteDept (Long id) {

        //find if department is existed or not

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Dept Id: " + id + " exist"));

//      delete department
//        No return statement since void
         departmentRepository.delete(department);
    }


    private DeptRespond mapToDeptEntity(Department dept) {
        return DeptRespond.builder()
                .deptId(dept.getDeptId())
                .deptName(dept.getDeptName())
                .deptAddress(dept.getDeptAddress())
                .deptCode(dept.getDeptCode())
                .build();
    }

    private DeptRespond mapToDeptEntity (Department dept, List<String> employeeNames){
        return DeptRespond.builder()
                .deptId(dept.getDeptId())
                .deptName(dept.getDeptName())
                .deptAddress(dept.getDeptAddress())
                .deptCode(dept.getDeptCode())
                .employeeNames(employeeNames)
                .build();
    }

}
