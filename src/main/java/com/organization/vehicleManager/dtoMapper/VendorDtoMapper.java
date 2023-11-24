package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.VendorRequest;
import com.organization.vehicleManager.dto.VendorResponse;
import com.organization.vehicleManager.entity.Company;
import com.organization.vehicleManager.entity.Vendor;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@Component
public class VendorDtoMapper {

    public static Vendor mapToEntity(VendorRequest vendorRequest, CompanyService companyService){
        Vendor vendor = new Vendor();
        vendor.setName(vendorRequest.getName());
        vendor.setAddress(vendorRequest.getAddress());
        vendor.setContact(vendorRequest.getContact());
        vendor.setRegNo(vendorRequest.getRegNo());

       List<Company> companies = vendorRequest.getCompanyId().stream().map(companyService::getEntity).toList();
       if (companies.isEmpty()){
           log.error("VendorDtoMapper.mapToVendor():: companies not found in db");
           throw new GeneralBusinessException("Company List is empty");}
       Set<Company> setCompany= new HashSet<>(companies);
       vendor.setCompany(setCompany);
       return vendor;

    }

    public static VendorResponse mapToResponse(Vendor vendor){
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setId(vendor.getId());
        vendorResponse.setName(vendor.getName());
        vendorResponse.setAddress(vendor.getAddress());
        vendorResponse.setContact(vendor.getContact());
        vendorResponse.setRegNo(vendor.getRegNo());
        vendorResponse.setCompanyName(vendor.getCompany().stream().map(Company::getName).toList());
        return vendorResponse;

    }
}
