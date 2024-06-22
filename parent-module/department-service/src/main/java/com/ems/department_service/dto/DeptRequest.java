package com.ems.department_service.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeptRequest {

    private String deptName;
    private String deptAddress;
    private String deptCode;
}
