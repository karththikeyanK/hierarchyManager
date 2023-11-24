package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.OrganizationRequest;
import com.organization.vehicleManager.dto.OrganizationResponse;
import com.organization.vehicleManager.entity.Organization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrganizationDtoMapper {
    public static Organization convertToEntity(OrganizationRequest organizationRequest) {
        return Organization.builder()
                .name(organizationRequest.getName())
                .address(organizationRequest.getAddress())
                .contact(organizationRequest.getContact())
                .regNo(organizationRequest.getRegNo())
                .build();
    }

    public static OrganizationResponse convertToResponse(Organization organization) {

        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setId(organization.getId());
        organizationResponse.setName(organization.getName());
        organizationResponse.setAddress(organization.getAddress());
        organizationResponse.setContact(organization.getContact());
        organizationResponse.setRegNo(organization.getRegNo());
        return organizationResponse;
    }
}
