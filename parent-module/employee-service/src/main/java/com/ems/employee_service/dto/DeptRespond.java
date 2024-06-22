package com.ems.employee_service.dto;

import lombok.*;

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
}
