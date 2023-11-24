package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.DepartmentRequest;
import com.organization.vehicleManager.dto.DepartmentResponse;
import com.organization.vehicleManager.entity.Company;
import com.organization.vehicleManager.entity.Department;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepartmentDtoMapper {
    public static Department convertToEntity(DepartmentRequest departmentRequest, CompanyService companyService){
        Company company = companyService.getEntity(departmentRequest.getCompanyId());
        Department department = Department.builder()
                .name(departmentRequest.getName())
                .code(departmentRequest.getCode())
                .company(company)
                .build();
        if (department==null){
            log.error("Department Dto Mapper::Converting to entity failed because of null object");
            throw new GeneralBusinessException("Convert to entity failed because of null object");
        }
        log.info("Department Dto Mapper::Converting to entity completed");
        return department;

    }


    public static DepartmentResponse convertToResponse(Department department){
        log.info("Department Dto Mapper::Converting to response started");

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        departmentResponse.setCode(department.getCode());
        departmentResponse.setCompanyId(department.getCompany().getId());
        if (departmentResponse==null){
            log.error("Department Dto Mapper::Converting to response failed because of null object");
            throw new GeneralBusinessException("Convert to response failed because of null object");
        }
        log.info("Department Dto Mapper::Converting to response completed");
        return departmentResponse;


    }
}
