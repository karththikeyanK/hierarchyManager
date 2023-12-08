package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationResponse implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String contact;
    private String regNo;

}
