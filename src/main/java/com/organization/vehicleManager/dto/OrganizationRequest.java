package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequest implements Serializable {
    private String name;
    private String address;
    private String contact;
    private String regNo;

}
