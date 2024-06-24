package com.ems.department_service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptRespondForAllDept {

    private Long deptId;
    private String deptName;
    private String deptAddress;
    private String deptCode;


}
