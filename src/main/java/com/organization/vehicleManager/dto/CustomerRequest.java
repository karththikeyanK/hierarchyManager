package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String contactNo;
    private String address;
    private List<Integer> companyIds;
}
