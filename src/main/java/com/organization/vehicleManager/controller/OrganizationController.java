package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.OrganizationAndCompanyAndCustomerRequest;
import com.organization.vehicleManager.dto.OrganizationAndCompanyRequest;
import com.organization.vehicleManager.dto.OrganizationRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.facade.CustomerFacade;
import com.organization.vehicleManager.facade.OrganizationFacade;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationFacade organizationFacade;
    private final CustomerFacade customerFacade;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrganization(@RequestBody OrganizationRequest organizationRequest){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Organization created successfully", organizationService.createOrganization(organizationRequest)));
        }catch (GeneralBusinessException e){
            log.error("OrganizationController::General Exception:Error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @PostMapping("/createOrganizationAndCompany")
    public ResponseEntity<ApiResponse> createOrganizationAndCompany(@RequestBody OrganizationAndCompanyRequest organizationAndCompanyRequest){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Organization & Companies created successfully", organizationFacade.createOrganizationAndCompany(organizationAndCompanyRequest)));
        }catch (GeneralBusinessException e){
            log.error("OrganizationController::General Exception:Error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @GetMapping("/getOrganizationAndCompany/{organizationId}")
    public ResponseEntity<ApiResponse> getOrganizationAndCompany(@PathVariable Integer organizationId){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Organization & Companies fetched successfully", organizationFacade.getOrganizationAndCompany(organizationId)));
        }catch (GeneralBusinessException e){
            log.error("OrganizationController::General Exception:Error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getOrganization(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "Organization fetched successfully", organizationService.getOrganization(id)));
        }catch (GeneralBusinessException e){
            log.error("General Exception:Error while getting organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while getting organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllOrganization(){
        try{
            return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "All organizations fetched successfully", organizationService.getAllOrganization()));
        }catch (GeneralBusinessException e){
            log.error("General Exception:Error while getting all organizations: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while getting all organizations: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateOrganization(@PathVariable Integer id, @RequestBody OrganizationRequest organizationRequest){
        try{
            return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "Organization updated successfully", organizationService.updateOrganization(id, organizationRequest)));
        }catch (GeneralBusinessException e){
            log.error("General Exception:Error while updating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while updating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteOrganization(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(new ApiResponse<>(ApiResponse.SUCCESS, "Organization deleted successfully", organizationService.deleteOrganization(id)));
        }catch (GeneralBusinessException e){
            log.error("General Exception:Error while deleting organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while deleting organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

    @GetMapping("/getOrganizationAndCompanyAndCustomer/{organizationId}")
    public ResponseEntity<ApiResponse> getOrganizationAndCompanyAndCustomer(@PathVariable Integer organizationId){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Organization & Companies & Customers fetched successfully",customerFacade.getAllOrganizationAndCompanyAndCustomer(organizationId)));
        }catch (GeneralBusinessException e){
            log.error("OrganizationController::General Exception:Error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

    @PostMapping("/createOrganizationAndCompanyAndCustomer")
    public ResponseEntity<ApiResponse> createOrganizationAndCompanyAndCustomer(@RequestBody OrganizationAndCompanyAndCustomerRequest organizationAndCompanyRequest){
        try{
            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Organization & Companies & Customers created successfully",customerFacade.createOrganizationAndCompanyAndCustomer(organizationAndCompanyRequest)));
        }catch (GeneralBusinessException e){
            log.error("OrganizationController::General Exception:Error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating organization: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

//    @GetMapping("/customQuery")
//    public ResponseEntity<ApiResponse> customQuery(@RequestBody String query){
//        try{
//            return ResponseEntity.ok().body( new ApiResponse<>(ApiResponse.SUCCESS, "Custom query executed successfully", organizationService.customQuery(query)));
//        }catch (GeneralBusinessException e){
//            log.error("OrganizationController::General Exception:Error while executing custom query: ", e);
//            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
//        }
//        catch (Exception e){
//            log.error("Unexpected error while executing custom query: ", e);
//            return ResponseEntity.badRequest().body(new ApiResponse<>(ApiResponse.ERROR, e.getMessage(), null));
//        }
//    }


}
