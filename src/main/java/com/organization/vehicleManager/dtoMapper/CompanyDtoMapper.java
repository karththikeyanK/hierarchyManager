package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.CompanyRequest;
import com.organization.vehicleManager.dto.CompanyResponse;
import com.organization.vehicleManager.entity.Company;
import com.organization.vehicleManager.entity.Organization;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompanyDtoMapper {

    public static Company convertToEntity(CompanyRequest companyRequest, OrganizationService organizationService) {
        log.info("CompanyDtoMapper::Converting company request to entity started");
        try{
            Organization organization = organizationService.getOrganizationEntity(companyRequest.getOrganizationId());
            Company company= Company.builder()
                    .name(companyRequest.getName())
                    .regNo(companyRequest.getRegNo())
                    .address(companyRequest.getAddress())
                    .contact(companyRequest.getContact())
                    .email(companyRequest.getEmail())
                    .organization(organization)
                    .build();
            log.info("CompanyDtoMapper::Converting company request to entity completed");
            return company;
        }catch (Exception e) {
            log.error("CompanyDtoMapper::Error while converting company request to entity: {}", e.getMessage());
            throw e;
        }
    }

    public static CompanyResponse convertToResponse(Company company) {
        log.info("CompanyDtoMapper::Converting company entity to response started");
        try{
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setId(company.getId());
            companyResponse.setName(company.getName());
            companyResponse.setRegNo(company.getRegNo());
            companyResponse.setAddress(company.getAddress());
            companyResponse.setContact(company.getContact());
            companyResponse.setEmail(company.getEmail());
            companyResponse.setOrganizationId(company.getOrganization().getId());
            log.info("CompanyDtoMapper::Converting company entity to response completed");
            return companyResponse;
        }catch (Exception e) {
            log.error("CompanyDtoMapper::Error while converting company entity to response: {}", e.getMessage());
            throw e;
        }
    }
}
