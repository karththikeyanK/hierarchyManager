package com.organization.vehicleManager.service;


import com.organization.vehicleManager.dto.OrganizationRequest;
import com.organization.vehicleManager.dto.OrganizationResponse;
import com.organization.vehicleManager.dtoMapper.OrganizationDtoMapper;
import com.organization.vehicleManager.entity.Organization;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationResponse createOrganization(OrganizationRequest organizationRequest) {
        log.info("Organization Service: Creating organization started");
        try{

            if (organizationRepository.existsByName(organizationRequest.getName())){
                log.error("Organization Service: Organization name already exists");
                throw new GeneralBusinessException("Organization name already exists");
            }

            if (organizationRepository.existsByRegNo(organizationRequest.getRegNo())){
                log.error("Organization Service: Organization registration number already exists");
                throw new GeneralBusinessException("Organization registration number already exists");
            }
            Organization org = organizationRepository.save(OrganizationDtoMapper.convertToEntity(organizationRequest));
            log.info("Organization Service: Organization object created");
            OrganizationResponse organizationResponse = OrganizationDtoMapper.convertToResponse(org);
            log.info("Organization Service: Creating organization completed");
            return organizationResponse;

        }catch (GeneralBusinessException ex){
            log.error("Organization Service: Error while creating organization: "+ ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("Organization Service: Unexpected error while creating organization: "+ ex.getMessage());
            throw ex;
        }
    }

    public OrganizationResponse getOrganization(Integer id){
        log.info("Organization Service: Getting organization started");
        try{
            Organization org = organizationRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Organization with id " + id + " does not exist"));
            log.info("Organization Service: Organization object retrieved");

            OrganizationResponse organizationResponse = OrganizationDtoMapper.convertToResponse(org);
            log.info("Organization Service: Getting organization completed");
            return organizationResponse;
        }catch (GeneralBusinessException ex){
            log.error("Organization Service: Error while getting organization: "+ ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("Organization Service: Unexpected error while getting organization: "+ ex.getMessage());
            throw ex;
        }
    }

    public List<OrganizationResponse> getAllOrganization() {
        log.info("Organization Service: Getting all organizations started");
        List<Organization> org = organizationRepository.findAll();

        if (org.isEmpty()) {
            log.error("Organization Service: No organizations found");
            throw new GeneralBusinessException("No organizations found");
        }

        List<OrganizationResponse> organizationResponseList = org.stream()
                .map(OrganizationDtoMapper::convertToResponse)
                .collect(Collectors.toList());

        log.info("Organization Service: Getting all organizations completed");
        return organizationResponseList;
    }


    public OrganizationResponse updateOrganization(Integer id, OrganizationRequest organizationRequest){
        log.info("Organization Service: Updating organization started");
        try{
            Organization organization = organizationRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Organization with id " + id + " does not exist"));
            log.info("Organization Service: Organization object retrieved");

            if (organizationRequest.getName().isEmpty() || organizationRequest.getRegNo().isEmpty() || organizationRequest.getName() == null || organizationRequest.getRegNo() == null || organizationRequest.getContact() == null || organizationRequest.getAddress() == null || organizationRequest.getContact().isEmpty() || organizationRequest.getAddress().isEmpty()){
                throw new GeneralBusinessException("Organization name or registration number or contact or address cannot be empty");
            }

            if (organizationRepository.existsByName(organizationRequest.getName()) && !organizationRequest.getName().equals(organization.getName())){
                log.error("Organization Service: Organization name already exists");
                throw new GeneralBusinessException("Organization name already exists");
            }

            if (organizationRepository.existsByRegNo(organizationRequest.getRegNo()) && !organizationRequest.getRegNo().equals(organization.getRegNo())){
                log.error("Organization Service: Organization registration number already exists");
                throw new GeneralBusinessException("Organization registration number already exists");
            }
            Organization savedOrg = OrganizationDtoMapper.convertToEntity(organizationRequest);
            savedOrg.setId(id);
            organizationRepository.save(savedOrg);
            log.info("Organization Service: Organization object updated");
            OrganizationResponse organizationResponse = OrganizationDtoMapper.convertToResponse(savedOrg);
            log.info("Organization Service: Updating organization completed");
            return organizationResponse;

        }catch (GeneralBusinessException ex){
            log.error("Organization Service: Error while updating organization: "+ ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("Organization Service: Unexpected error while updating organization: "+ ex.getMessage());
            throw ex;
        }
    }

    public String deleteOrganization(Integer id){
        log.info("Organization Service: Deleting organization started");
        try{
            Organization org = organizationRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Organization with id " + id + " does not exist"));
            log.info("Organization Service: Organization object retrieved");
            organizationRepository.delete(org);
            log.info("Organization Service: Organization object deleted");
            return "Organization deleted successfully";
        }catch (GeneralBusinessException ex){
            log.error("Organization Service: Error while deleting organization: "+ ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("Organization Service: Unexpected error while deleting organization: "+ ex.getMessage());
            throw ex;
        }
    }



    public Organization getOrganizationEntity(Integer orgId) {
        try{
            log.info("Organization Service: Getting organization entity started");
            Organization organization = organizationRepository.findById(orgId).orElseThrow(() -> new GeneralBusinessException("Organization with id " + orgId + " does not exist"));
            log.info("Organization Service: Getting organization entity completed");
            return organization;
        }catch (GeneralBusinessException ex){
            log.error("Organization Service: Error while getting organization entity: "+ ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("Organization Service: Unexpected error while getting organization entity: "+ ex.getMessage());
            throw ex;
        }
    }

//    public OrganizationResponse customQuery(String query){
//        try{
//            log.info("Organization Service: Custom query started");
//            Organization org = organizationRepository.findByCustomQuery(query);
//            log.info("Organization Service: Custom query completed");
//            if (org == null) {
//                log.error("Organization Service: No organizations found");
//                throw new GeneralBusinessException("No organizations found");
//            }
//
//            OrganizationResponse organizationResponse = OrganizationDtoMapper.convertToResponse(org);
//            return organizationResponse;
//        }
//        catch (GeneralBusinessException ex){
//            log.error("Organization Service: Error while custom query: "+ ex.getMessage());
//            throw ex;
//        }catch (Exception ex){
//            log.error("Organization Service: Unexpected error while custom query: "+ ex.getMessage());
//            throw ex;
//        }
//
//    }


}
