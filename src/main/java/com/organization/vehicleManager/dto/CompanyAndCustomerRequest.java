package com.organization.vehicleManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAndCustomerRequest {
    private CompanyRequest companyRequest;
    private List<CustomerRequest> customerRequestList;
}
