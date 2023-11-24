package com.organization.vehicleManager.facade;

import com.organization.vehicleManager.dto.CompanyResponse;
import com.organization.vehicleManager.dto.OrganizationAndCompanyRequest;
import com.organization.vehicleManager.dto.OrganizationAndCompanyResponse;
import com.organization.vehicleManager.dto.OrganizationResponse;
import com.organization.vehicleManager.dtoMapper.OrganizationDtoMapper;
import com.organization.vehicleManager.entity.Organization;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.CompanyService;
import com.organization.vehicleManager.service.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrganizationFacade {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public OrganizationAndCompanyResponse createOrganizationAndCompany(OrganizationAndCompanyRequest organizationAndCompanyRequest){
        try{
            log.info("OrganizationFacade.createOrganizationAndCompany():::START");
            OrganizationResponse organizationResponse =organizationService.createOrganization(organizationAndCompanyRequest.getOrganizationRequest());
            log.info("OrganizationFacade.createOrganizationAndCompany():::Organization created successfully");
            List<CompanyResponse> companyResponseList = new ArrayList<>();

            if (organizationAndCompanyRequest.getCompanyRequestList().isEmpty()){
                log.error("OrganizationFacade.createOrganizationAndCompany():::Organization create failed");
                throw new GeneralBusinessException("Organization create failed");
            }

            organizationAndCompanyRequest.getCompanyRequestList().forEach(companyRequest -> {
                companyRequest.setOrganizationId(organizationResponse.getId());
                companyResponseList.add(companyService.create(companyRequest));
            });
            if (companyResponseList.isEmpty()){
                log.error("OrganizationFacade.createOrganizationAndCompany():::Company List is empty");
                throw new GeneralBusinessException("Company List is empty");
            }
            log.info("OrganizationFacade.createOrganizationAndCompany():::Company created successfully");

            OrganizationAndCompanyResponse organizationAndCompanyResponse = new OrganizationAndCompanyResponse();
            organizationAndCompanyResponse.setOrganizationResponse(organizationResponse);
            organizationAndCompanyResponse.setCompanyResponseList(companyResponseList);
            if (organizationAndCompanyResponse == null){
                log.error("OrganizationFacade.createOrganizationAndCompany():::OrganizationAndCompanyResponse is null");
                throw new GeneralBusinessException("OrganizationAndCompanyResponse is null");
            }
            log.info("OrganizationFacade.createOrganizationAndCompany():::END");
            return organizationAndCompanyResponse;
        }catch (GeneralBusinessException e){
            throw e;
        }catch (Exception e){
            log.error("OrganizationFacade.createOrganizationAndCompany():::Exception occurred:::{}",e.getMessage());
            throw e;
        }
    }

    public OrganizationAndCompanyResponse getOrganizationAndCompany(Integer organizationId){
        try{
            log.info("OrganizationFacade.getOrganizationAndCompany():::START");
            Organization organization = organizationService.getOrganizationEntity(organizationId);
            if (organization == null){
                log.error("OrganizationFacade.getOrganizationAndCompany():::Organization not found in db");
                throw new GeneralBusinessException("Organization not found in db");
            }
            log.info("OrganizationFacade.getOrganizationAndCompany():::Organization retrieved successfully");

            List<CompanyResponse> companyResponseList = companyService.getByOrganizationId(organizationId);
            if (companyResponseList.isEmpty()){
                log.error("OrganizationFacade.getOrganizationAndCompany():::Company List is empty");
                throw new GeneralBusinessException("Company List is empty");
            }
            log.info("OrganizationFacade.getOrganizationAndCompany():::Company retrieved successfully");

            OrganizationAndCompanyResponse organizationAndCompanyResponse = new OrganizationAndCompanyResponse();
            organizationAndCompanyResponse.setOrganizationResponse(OrganizationDtoMapper.convertToResponse(organization));
            organizationAndCompanyResponse.setCompanyResponseList(companyResponseList);
            return organizationAndCompanyResponse;
        }catch (GeneralBusinessException e){
            throw e;
        }catch (Exception e){
            log.error("OrganizationFacade.getOrganizationAndCompany():::Exception occurred:::{}",e.getMessage());
            throw e;
        }
    }
}
