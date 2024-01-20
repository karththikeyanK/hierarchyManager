package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.CustomerResponse;
import com.organization.vehicleManager.entity.Company;
import com.organization.vehicleManager.entity.Customer;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class CustomerDtoMapper {
    public static Customer convertToEntity(CustomerRequest customerRequest, CompanyService companyService) {
        Set<Company> companies = new HashSet<>();
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .contactNumber(customerRequest.getContactNo())
                .verified(customerRequest.getVerified())
                .reason(customerRequest.getReason())
                .build();
        log.info("CustomerDtoMapper::Set<Company> companies started");
        customerRequest.getCompanyIds().forEach(companyId -> {
            Company company = companyService.getEntity(companyId);
            companies.add(company);
        });
        log.info("CustomerDtoMapper::Set<Company> companies completed");
        if (companies.isEmpty()){
            log.error("CustomerDtoMapper::Set<Company> companies is empty");
            throw new GeneralBusinessException("Set<Company> companies is empty");
        }
        customer.setCompany(companies);
        return customer;
    }

    public static CustomerResponse convertToResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setName(customer.getName());
        customerResponse.setAddress(customer.getAddress());
        customerResponse.setContactNo(customer.getContactNumber());
        customerResponse.setVerified(customer.getVerified());
        customerResponse.setReason(customer.getReason());
        List<String> companyNames = new ArrayList<>();
        List<Integer> companyIds = new ArrayList<>();

        customer.getCompany().forEach(company -> {
            companyNames.add(company.getName());
            companyIds.add(company.getId());
        });

        customerResponse.setCompanyNames(companyNames);
        customerResponse.setCompanyIds(companyIds);
        return customerResponse;

    }


}


