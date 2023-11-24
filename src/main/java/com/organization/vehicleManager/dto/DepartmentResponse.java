package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Integer id;
    private String name;
    private String code;
    private Integer companyId;
}
