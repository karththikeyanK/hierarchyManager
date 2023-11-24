package com.organization.vehicleManager.service;

import com.organization.vehicleManager.dto.CompanyRequest;
import com.organization.vehicleManager.dto.CompanyResponse;
import com.organization.vehicleManager.dtoMapper.CompanyDtoMapper;
import com.organization.vehicleManager.entity.Company;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrganizationService organizationService;

    public CompanyResponse create(CompanyRequest companyRequest){
        log.info("Company Service::Creating company started");
        try{
            if(companyRepository.existsByName(companyRequest.getName())){
                log.error("Company Service::Company already exists with name: {}", companyRequest.getName());
                throw new GeneralBusinessException("Company already exists with name: " + companyRequest.getName());
            }
            if (companyRepository.existsByRegNo(companyRequest.getRegNo())){
                log.error("Company Service::Company already exists with regNo: {}", companyRequest.getRegNo());
                throw new GeneralBusinessException("Company already exists with regNo: " + companyRequest.getRegNo());
            }
            Company company = CompanyDtoMapper.convertToEntity(companyRequest, organizationService);
            company = companyRepository.save(company);
            log.info("Company Service::Company created successfully");
            return CompanyDtoMapper.convertToResponse(company);
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Error while creating company: {}", e.getMessage());
            throw e;
        }
    }


    public CompanyResponse update(Integer id, CompanyRequest companyRequest){
        log.info("Company Service::Updating company started");
        try{
            if (id == null) {
                log.error("Company Service::update()::Company id is null");
                throw new GeneralBusinessException("Company id is null");
            }
            Company company = companyRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Company not found with id: " + id ));
            if(companyRepository.existsByName(companyRequest.getName()) && !company.getName().equals(companyRequest.getName())){
                log.error("Company Service::Company already exists with name: {}", companyRequest.getName());
                throw new GeneralBusinessException("Company already exists with name: " + companyRequest.getName());
            }
            if (companyRepository.existsByRegNo(companyRequest.getRegNo()) && !company.getRegNo().equals(companyRequest.getRegNo())){
                log.error("Company Service::Company already exists with regNo: {}", companyRequest.getRegNo());
                throw new GeneralBusinessException("Company already exists with regNo: " + companyRequest.getRegNo());
            }
            company = CompanyDtoMapper.convertToEntity(companyRequest, organizationService);
            company.setId(id);
            company = companyRepository.save(company);
            log.info("Company Service::Company updated successfully");
            return CompanyDtoMapper.convertToResponse(company);
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Error while updating company: {}", e.getMessage());
            throw e;
        }
    }

    public String delete(Integer id){
        log.info("Company Service::Deleting company started");
        try{
            if (id == null) {
                log.error("Company Service::delete()::Company id is null");
                throw new GeneralBusinessException("Company id is null");
            }
            Company company = companyRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Company not found with id: " + id ));
            companyRepository.delete(company);
            log.info("Company Service::Company deleted successfully");
            return "Company deleted successfully";
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Company Service::Error while deleting company: {}", e.getMessage());
            throw e;
        }
    }

    public List<CompanyResponse> getAll(){
        log.info("Company Service::Getting all companies started");
        try{
            List<Company> companies = companyRepository.findAll();
            if (companies.isEmpty()){
                log.error("Company Service::Companies not found in db");
                throw new GeneralBusinessException("Companies not found in db");
            }
            else
                log.info("Company Service::Companies retrieved successfully");

            return companies.stream().map(CompanyDtoMapper::convertToResponse).toList();
        }catch (Exception e) {
            log.error("Company Service::Un Expected Error while getting all companies: {}", e.getMessage());
            throw e;
        }
    }

    public Company getEntity(Integer id){
        log.info("Company Service::Getting company started");
        try {
            if (id == null) {
                log.error("Company Service::getEntity::Company id is null");
                throw new GeneralBusinessException("Company id is null");
            }
            Company company = companyRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Company not found with id: " + id ));
            log.info("Company Service::Company retrieved successfully");
            return company;
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Company service::Un Expected Error while getting company: {}", e.getMessage());
            throw e;
        }

    }

    public CompanyResponse getById(Integer id){
        log.info("Company Service::Getting company started");
        try{
            Company company = companyRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Company not found with id: " + id ));
            log.info("Company Service::Company retrieved successfully");
            return CompanyDtoMapper.convertToResponse(company);
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Company service::Un Expected Error while getting company: {}", e.getMessage());
            throw e;
        }

    }

    public List<CompanyResponse> getByOrganizationId(Integer organizationId){
        log.info("Company Service::Getting company started");
        try{
            List<Company> companies = companyRepository.findByOrganizationId(organizationId);
            if (companies.isEmpty()){
                log.error("Company Service::Companies not found in db");
                throw new GeneralBusinessException("Companies not found in db");
            }
            else
                log.info("Company Service::Companies retrieved successfully");

            return companies.stream().map(CompanyDtoMapper::convertToResponse).toList();
        }catch (Exception e) {
            log.error("Company service::Un Expected Error while getting company: {}", e.getMessage());
            throw e;
        }

    }
}
