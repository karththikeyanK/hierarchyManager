package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyRequest implements Serializable {
    private String name;
    private String regNo;
    private String address;
    private String contact;
    private String email;
    private Integer organizationId;
}
