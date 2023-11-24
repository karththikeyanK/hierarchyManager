package com.organization.vehicleManager.service;

import com.organization.vehicleManager.dto.VendorRequest;
import com.organization.vehicleManager.dto.VendorResponse;
import com.organization.vehicleManager.dtoMapper.VendorDtoMapper;
import com.organization.vehicleManager.entity.Vendor;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.organization.vehicleManager.dtoMapper.VendorDtoMapper.mapToEntity;
import static com.organization.vehicleManager.dtoMapper.VendorDtoMapper.mapToResponse;

@Service
@Slf4j
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CompanyService companyService;

    public VendorResponse create(VendorRequest vendorRequest){
       try {
              log.info("VendorService.create():::START");
              if (vendorRequest == null) {
                  log.error("VendorService.create()::: vendorRequest is null");
                  throw new GeneralBusinessException("vendorRequest is null");
              }
              requestValidation(vendorRequest);
               if (vendorRepository.existsByRegNo(vendorRequest.getRegNo())){
                   log.error("VendorService.isValidate()::: regNo already exist");
                   throw new GeneralBusinessException("regNo already exist");
               }
               if (vendorRepository.existsByContact(vendorRequest.getContact())){
                   log.error("VendorService.isValidate()::: contact already exist");
                   throw new GeneralBusinessException("contact already exist");
               }
               Vendor vendor = mapToEntity(vendorRequest,companyService);
               vendorRepository.save(vendor);
               log.info("VendorService.create():::END");
               return mapToResponse(vendor);

       }catch (GeneralBusinessException e){
           log.error("VendorService.create()::: GeneralBusinessException occurred --> {}", e.getMessage());
           throw e;
       }catch (Exception ex) {
           log.error("VendorService.create():: Exception occurred --> {}", ex.getMessage());
           throw ex;
       }
    }

    public VendorResponse update(Integer id,VendorRequest vendorRequest){
        try {
            log.info("VendorService.update():::START");
            if (vendorRequest == null) {
                log.error("VendorService.update()::: vendorRequest is null");
                throw new GeneralBusinessException("vendorRequest is null");
            }
            requestValidation(vendorRequest);
            Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Vendor not found for id: " + id));
            if (vendorRepository.existsByRegNo(vendorRequest.getRegNo()) && !vendorRequest.getRegNo().equals(vendor.getRegNo())){
                log.error("VendorService.isUpdateValidate()::: regNo already exist");
                throw new GeneralBusinessException("regNo already exist");
            }
            if (vendorRepository.existsByContact(vendorRequest.getContact()) && !vendorRequest.getContact().equals(vendor.getContact())){
                log.error("VendorService.isUpdateValidate()::: contact already exist");
                throw new GeneralBusinessException("contact already exist");
            }
            vendor= mapToEntity(vendorRequest,companyService);
            vendor.setId(id);
            vendorRepository.save(vendor);
            log.info("VendorService.update():::END");
            return mapToResponse(vendor);

        }catch (GeneralBusinessException e){
            log.error("VendorService.update()::: GeneralBusinessException occurred --> {}", e.getMessage());
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.update():: Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    public VendorResponse getById(Integer id){
        try {
            log.info("VendorService.getById():::START");
            Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Vendor not found for id: " + id));
            log.info("VendorService.getById():::END");
            return mapToResponse(vendor);
        }catch (GeneralBusinessException e){
            log.error("VendorService.getById()::: GeneralBusinessException occurred --> {}", e.getMessage());
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.getById():: Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    public List<VendorResponse> getAll(){
        try {
            log.info("VendorService.getAll():::START");
            List<Vendor> vendors = vendorRepository.findAll();
            log.info("VendorService.getAll():::END");
            return vendors.stream().map(VendorDtoMapper::mapToResponse).toList();
        }catch (GeneralBusinessException e){
            log.error("VendorService.getAll()::: GeneralBusinessException occurred --> {}", e.getMessage());
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.getAll():: Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    public String delete(Integer id){
        try {
            log.info("VendorService.delete():::START");
            Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Vendor not found for id: " + id));
            vendorRepository.delete(vendor);
            log.info("VendorService.delete():::END");
            return "Vendor deleted successfully";
        }catch (GeneralBusinessException e){
            log.error("VendorService.delete()::: GeneralBusinessException occurred --> {}", e.getMessage());
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.delete():: Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    public Vendor getEntity(Integer id){
        try {
            log.info("VendorService.getEntity():::START");
            Vendor vendor = vendorRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Vendor not found for id: " + id));
            log.info("VendorService.getEntity():::END");
            return vendor;
        }catch (GeneralBusinessException e){
            log.error("VendorService.getEntity()::: GeneralBusinessException occurred --> {}", e.getMessage());
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.getEntity():: Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    public void requestValidation(VendorRequest vendorRequest){
     try {
            log.info("VendorService.requestValidation():::START");
            if (vendorRequest.getName() == null || vendorRequest.getName().isEmpty()) {
                log.error("VendorService.requestValidation()::: name is null or empty");
                throw new GeneralBusinessException("name is null or empty");
            }
            if (vendorRequest.getRegNo() == null || vendorRequest.getRegNo().isEmpty()) {
                log.error("VendorService.requestValidation()::: regNo is null or empty");
                throw new GeneralBusinessException("regNo is null or empty");
            }
            if (vendorRequest.getAddress() == null || vendorRequest.getAddress().isEmpty()) {
                log.error("VendorService.requestValidation()::: address is null or empty");
                throw new GeneralBusinessException("address is null or empty");
            }
            if (vendorRequest.getContact() == null || vendorRequest.getContact().isEmpty()) {
                log.error("VendorService.requestValidation()::: contact is null or empty");
                throw new GeneralBusinessException("contact is null or empty");
            }
            if (vendorRequest.getCompanyId() == null || vendorRequest.getCompanyId().isEmpty()) {
                log.error("VendorService.requestValidation()::: companyId is null or empty");
                throw new GeneralBusinessException("companyId is null or empty");
            }
            log.info("VendorService.requestValidation():::END");
        }catch (GeneralBusinessException e){
            throw e;
        }catch (Exception ex) {
            log.error("VendorService.requestValidation():: Exception occurred --> {}", ex.getMessage());
            throw ex;
     }
    }


}
