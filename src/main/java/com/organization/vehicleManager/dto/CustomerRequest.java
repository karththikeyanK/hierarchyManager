package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable {
    private String name;
    private String contactNo;
    private String address;
    private Boolean verified;
    private String reason;
    private List<Integer> companyIds;
}
