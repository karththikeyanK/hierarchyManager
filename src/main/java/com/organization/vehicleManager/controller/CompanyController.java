package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.CompanyRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCompany(@RequestBody CompanyRequest companyRequest){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Company created successfully", companyService.create(companyRequest)));
        }catch (GeneralBusinessException e){
            log.error("Company Controller::General Exception:Error while creating company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Company Controller::Unexpected error while creating company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCompany(@PathVariable Integer id, @RequestBody CompanyRequest companyRequest){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Company updated successfully", companyService.update(id, companyRequest)));
        }catch (GeneralBusinessException e){
            log.error("Company Controller::General Exception:Error while updating company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Company Controller::Unexpected error while updating company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getCompany(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Company fetched successfully", companyService.getById(id)));
        }catch (GeneralBusinessException e){
            log.error("Company Controller::General Exception:Error while getting company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Company Controller::Unexpected error while getting company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllCompany(){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "All companies fetched successfully", companyService.getAll()));
        }catch (GeneralBusinessException e){
            log.error("Company Controller::General Exception:Error while getting all companies: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Company Controller::Unexpected error while getting all companies: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Company deleted successfully", companyService.delete(id)));
        }catch (GeneralBusinessException e){
            log.error("Company Controller::General Exception:Error while deleting company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Company Controller::Unexpected error while deleting company: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }


}
