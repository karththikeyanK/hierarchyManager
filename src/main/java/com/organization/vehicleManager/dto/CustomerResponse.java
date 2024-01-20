package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String name;
    private String contactNo;
    private String address;
    private Boolean verified;
    private String reason;
    private List<String> companyNames;
    private List<Integer> companyIds;
}
