package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationAndCompanyRequest {
    private OrganizationRequest organizationRequest;
    private List<CompanyRequest> companyRequestList;
}
