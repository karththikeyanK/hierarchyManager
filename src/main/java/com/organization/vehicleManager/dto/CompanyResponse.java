package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private Integer id;
    private String name;
    private String regNo;
    private String address;
    private String contact;
    private String email;
    private Integer organizationId;
}
