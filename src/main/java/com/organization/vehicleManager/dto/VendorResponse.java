package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {
    private Integer id;
    private String name;
    private String regNo;
    private String address;
    private String contact;
    private List<String> companyName;
}
