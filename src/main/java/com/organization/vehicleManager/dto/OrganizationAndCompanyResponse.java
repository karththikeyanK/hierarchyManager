package com.organization.vehicleManager.dto;

import com.organization.vehicleManager.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationAndCompanyResponse {
    private OrganizationResponse organizationResponse;
    private List<CompanyResponse> companyResponseList;
}
