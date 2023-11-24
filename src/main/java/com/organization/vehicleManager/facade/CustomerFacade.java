package com.organization.vehicleManager.facade;

import com.organization.vehicleManager.dto.*;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.CompanyService;
import com.organization.vehicleManager.service.CustomerService;
import com.organization.vehicleManager.service.OrganizationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerFacade {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public OrganizationAndCompanyAndCustomerResponse createOrganizationAndCompanyAndCustomer(OrganizationAndCompanyAndCustomerRequest organizationAndCompanyAndCustomerRequest) {
        try {
            log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::START");
            OrganizationAndCompanyAndCustomerResponse organizationAndCompanyAndCustomerResponse = new OrganizationAndCompanyAndCustomerResponse();
            OrganizationResponse organizationResponse = organizationService.createOrganization(organizationAndCompanyAndCustomerRequest.getOrganizationRequest());
            if (organizationResponse == null) {
                log.error("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Organization create failed");
                throw new GeneralBusinessException("Organization create failed");
            }
            log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Organization created successfully");

            List<CompanyAndCustomerResponse> companyAndCustomerResponseList = new ArrayList<>();

            organizationAndCompanyAndCustomerRequest.getCompanyAndCustomerRequest().forEach(companyAndCustomerRequest -> {
                CompanyAndCustomerResponse companyAndCustomerResponse = new CompanyAndCustomerResponse();

                companyAndCustomerRequest.getCompanyRequest().setOrganizationId(organizationResponse.getId());
                CompanyResponse companyResponse = companyService.create(companyAndCustomerRequest.getCompanyRequest());
                if (companyResponse == null) {
                    log.error("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Company create failed");
                    throw new GeneralBusinessException("Company create failed");
                }
                log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer()::: {} Company created successfully", companyResponse.getName());

                List<CustomerResponse> customerResponseList = new ArrayList<>();
                companyAndCustomerRequest.getCustomerRequestList().forEach(customerRequest -> {
                    CustomerResponse newCustomerResponse ;
                    List<Integer> companyIdsList = new ArrayList<>();
                    CustomerResponse existingCustomerResponse = customerService.getCustomerEntityByContactNumber(customerRequest.getContactNo());
                    if (existingCustomerResponse != null) {
                        if (!existingCustomerResponse.getName().equals(customerRequest.getName())) {
                            log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer()::: {} Customer already exists", existingCustomerResponse.getName());
                            throw new GeneralBusinessException("Mobile number already exists with different name");
                        }
                        companyIdsList.addAll(existingCustomerResponse.getCompanyIds());
                        companyIdsList.add(companyResponse.getId());
                        customerRequest.setCompanyIds(companyIdsList);
                        newCustomerResponse = customerService.updateCustomer(existingCustomerResponse.getId(), customerRequest);
                        if (newCustomerResponse == null) {
                            log.error("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Customer Update failed");
                            throw new GeneralBusinessException("Customer create failed");
                        }
                        log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer()::: {} Customer updated successfully", newCustomerResponse.getName());
                    }else {
                        companyIdsList.add(companyResponse.getId());
                        customerRequest.setCompanyIds(companyIdsList);
                        newCustomerResponse = customerService.createCustomer(customerRequest);
                        if (newCustomerResponse == null) {
                            log.error("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Customer create failed");
                            throw new GeneralBusinessException("Customer create failed");
                        }
                        log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer()::: {} Customer created successfully", newCustomerResponse.getName());
                    }
                    customerResponseList.add(newCustomerResponse);
                    log.info("OrganizationFacade.createOrganizationAndCompanyAndCustomer()::: {} Customer created successfully", newCustomerResponse.getName());
                });

                companyAndCustomerResponse.setCompanyResponse(companyResponse);
                companyAndCustomerResponse.setCustomerResponseList(customerResponseList);
                companyAndCustomerResponseList.add(companyAndCustomerResponse);

            });

            organizationAndCompanyAndCustomerResponse.setOrganizationResponse(organizationResponse);
            organizationAndCompanyAndCustomerResponse.setCompanyAndCustomerResponse(companyAndCustomerResponseList);
            return organizationAndCompanyAndCustomerResponse;
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("OrganizationFacade.createOrganizationAndCompanyAndCustomer():::Exception occurred:::{}", e.getMessage());
            throw e;
        }
    }

    public OrganizationAndCompanyAndCustomerResponse getAllOrganizationAndCompanyAndCustomer(Integer orgId){
        try{
            log.info("OrganizationFacade.getAllOrganizationAndCompanyAndCustomer():::START");
            OrganizationAndCompanyAndCustomerResponse organizationAndCompanyAndCustomerResponse = new OrganizationAndCompanyAndCustomerResponse();
            organizationAndCompanyAndCustomerResponse.setOrganizationResponse(organizationService.getOrganization(orgId));

            if (organizationAndCompanyAndCustomerResponse.getOrganizationResponse() == null){
                log.error("OrganizationFacade.getAllOrganizationAndCompanyAndCustomer():::Organization not found in db");
                throw new GeneralBusinessException("Organization not found in db");
            }

            List<CompanyAndCustomerResponse> companyAndCustomerResponseList = new ArrayList<>();
            List<CompanyResponse> companyResponseList = companyService.getByOrganizationId(orgId);
            if (companyResponseList.isEmpty()){
                log.error("OrganizationFacade.getAllOrganizationAndCompanyAndCustomer():::Company List is empty");
                throw new GeneralBusinessException("Company Not Found Return Null");
            }

            companyResponseList.forEach(companyResponse -> {
                CompanyAndCustomerResponse companyAndCustomerResponse = new CompanyAndCustomerResponse();
                List<CustomerResponse> customerResponseList = customerService.getCustomerByCompanyId(companyResponse.getId());

                if (customerResponseList.isEmpty()){
                    log.error("OrganizationFacade.getAllOrganizationAndCompanyAndCustomer():::Customer List is empty");
                    throw new GeneralBusinessException("Customer Not Found Return Null");
                }
                companyAndCustomerResponse.setCompanyResponse(companyResponse);
                companyAndCustomerResponse.setCustomerResponseList(customerResponseList);
                companyAndCustomerResponseList.add(companyAndCustomerResponse);
            });
            organizationAndCompanyAndCustomerResponse.setCompanyAndCustomerResponse(companyAndCustomerResponseList);
            return organizationAndCompanyAndCustomerResponse;
        }catch (GeneralBusinessException e){
            throw e;
        }catch (Exception e){
            log.error("OrganizationFacade.getAllOrganizationAndCompanyAndCustomer():::Exception occurred:::{}",e.getMessage());
            throw e;
        }
    }
}
