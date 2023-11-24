package com.organization.vehicleManager.service;

import com.organization.vehicleManager.dto.DepartmentRequest;
import com.organization.vehicleManager.dto.DepartmentResponse;
import com.organization.vehicleManager.dtoMapper.DepartmentDtoMapper;
import com.organization.vehicleManager.entity.Department;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyService companyService;

    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest){
        log.info("Department Service::Creating department started");
        try{
            validation(departmentRequest);
            if (departmentRepository.existsByCode(departmentRequest.getCode())){
                log.error("Department Service::Validation failed because of code already exists");
                throw new GeneralBusinessException("Validation failed because of code already exists");
            }
            if (departmentRepository.existsByName(departmentRequest.getName())){
                log.error("Department Service::Validation failed because of name already exists");
                throw new GeneralBusinessException("Validation failed because of name already exists");
            }
            Department department = DepartmentDtoMapper.convertToEntity(departmentRequest, companyService);
            department = departmentRepository.save(department);
            log.info("Department Service::Department created successfully");
            return DepartmentDtoMapper.convertToResponse(department);

        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Error while creating department: {}", e.getMessage());
            throw e;
        }

    }

    public DepartmentResponse updateDepartment(Integer id, DepartmentRequest departmentRequest){
        log.info("Department Service::Updating department started");
        try{
            validation(departmentRequest);
            Department department = departmentRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Department not found with id: " + id ));
           if (departmentRepository.existsByCode(departmentRequest.getCode()) && !department.getCode().equals(departmentRequest.getCode())){
                log.error("Department Service::Validation failed because of code already exists");
                throw new GeneralBusinessException("Validation failed because of code already exists");
            }
            if (departmentRepository.existsByName(departmentRequest.getName()) && !department.getName().equals(departmentRequest.getName())){
                log.error("Department Service::Validation failed because of name already exists");
                throw new GeneralBusinessException("Validation failed because of name already exists");
            }
            department = DepartmentDtoMapper.convertToEntity(departmentRequest, companyService);
            department.setId(id);
            department = departmentRepository.save(department);
            log.info("Department Service::Department updated successfully");
            return DepartmentDtoMapper.convertToResponse(department);

        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Department Service::Error while updating department: {}", e.getMessage());
            throw e;
        }

    }

    public DepartmentResponse getDepartment(Integer id){
        log.info("Department Service::Getting department started");
        try{
            Department department = departmentRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Department not found with id: " + id ));
            log.info("Department Service::Department get successfully");
            return DepartmentDtoMapper.convertToResponse(department);
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Department Service::Error while getting department: {}", e.getMessage());
            throw e;
        }

    }

    public List<DepartmentResponse> getAllDepartment(){
        log.info("Department Service::Getting all department started");
        try{
            List<Department> departments = departmentRepository.findAll();
            log.info("Department Service::Department retrieved successfully"+departments.toString());
            if (departments.isEmpty()) {
                log.error("Department Service::Department not found");
                throw new GeneralBusinessException("Department not found");
            }
            log.info("Department Service::Department retrieved successfully");
            return departments.stream().map(DepartmentDtoMapper::convertToResponse).toList();
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Department Service::Error while getting department: {}", e.getMessage());
            throw e;
        }

    }

    public String deleteDepartment(Integer id){
        log.info("Department Service::Deleting department started");
        try{
            Department department = departmentRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Department not found with id: " + id ));
            departmentRepository.delete(department);
            log.info("Department Service::Department deleted successfully");
            return "Department deleted successfully";
        }catch (GeneralBusinessException ex){
            throw ex;
        }
        catch (Exception e) {
            log.error("Department Service::Error while deleting department: {}", e.getMessage());
            throw e;
        }

    }

    public Department getDepartmentEntity(Integer id){
        try{
            return departmentRepository.findById(id).orElseThrow(()-> new GeneralBusinessException("Department does not exist with id "+id));
        }catch (GeneralBusinessException ex){
            log.error("Department Service::Department does not exist with id "+id);
            throw ex;
        }catch (Exception e){
            log.error("Department Service::Unexpected Error -->"+e.getMessage());
            throw e;
        }
    }

    private void validation(DepartmentRequest departmentRequest) {
        if (departmentRequest.getCode()==null || departmentRequest.getCode().isEmpty()){
            log.error("Department Service::Validation failed because of code is null or empty");
            throw new GeneralBusinessException("Validation failed because of code is null or empty");
        }
        if (departmentRequest.getName()==null || departmentRequest.getName().isEmpty()){
            log.error("Department Service::Validation failed because of name is null or empty");
            throw new GeneralBusinessException("Validation failed because of name is null or empty");
        }
        if (departmentRequest.getCompanyId()==null){
            log.error("Department Service::Validation failed because of company id is null");
            throw new GeneralBusinessException("Validation failed because of company id is null");
        }
    }



}
