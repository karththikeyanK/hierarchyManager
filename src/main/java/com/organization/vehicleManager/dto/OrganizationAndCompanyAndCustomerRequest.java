package com.organization.vehicleManager.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationAndCompanyAndCustomerRequest {
    private OrganizationRequest organizationRequest;
    private List<CompanyAndCustomerRequest> companyAndCustomerRequest;
}
