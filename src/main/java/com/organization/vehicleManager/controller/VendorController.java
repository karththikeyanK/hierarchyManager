package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.VendorRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createVendor(@RequestBody VendorRequest vendorRequest){
        try{
            log.info("VendorController.createVendor():::START");
            return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS,"Vendor created successfully",vendorService.create(vendorRequest)));
        }catch (GeneralBusinessException ex){
            log.error("VendorController.createVendor():::GeneralBusinessException occurred while creating vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,ex.getMessage(),null));
        }
        catch (Exception e){
            log.error("VendorController.createVendor():::Exception occurred while creating vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getVendor(@PathVariable Integer id){
        try{
            log.info("VendorController.getVendor():::START");
            return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS,"Vendor fetched successfully",vendorService.getById(id)));
        }catch (GeneralBusinessException ex){
            log.error("VendorController.getVendor():::GeneralBusinessException occurred while fetching vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,ex.getMessage(),null));
        }
        catch (Exception e){
            log.error("VendorController.getVendor():::Exception occurred while fetching vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllVendor(){
        try{
            log.info("VendorController.getAllVendor():::START");
            return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS,"Vendor fetched successfully",vendorService.getAll()));
        }catch (GeneralBusinessException ex){
            log.error("VendorController.getAllVendor():::GeneralBusinessException occurred while fetching vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,ex.getMessage(),null));
        }
        catch (Exception e){
            log.error("VendorController.getAllVendor():::Exception occurred while fetching vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateVendor(@PathVariable Integer id,@RequestBody VendorRequest vendorRequest){
        try{
            log.info("VendorController.updateVendor():::START");
            return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS,"Vendor updated successfully",vendorService.update(id,vendorRequest)));
        }catch (GeneralBusinessException ex){
            log.error("VendorController.updateVendor():::GeneralBusinessException occurred while updating vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,ex.getMessage(),null));
        }
        catch (Exception e){
            log.error("VendorController.updateVendor():::Exception occurred while updating vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteVendor(@PathVariable Integer id){
        try{
            log.info("VendorController.deleteVendor():::START");
            return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS,"Vendor deleted successfully",vendorService.delete(id)));
        }catch (GeneralBusinessException ex){
            log.error("VendorController.deleteVendor():::GeneralBusinessException occurred while deleting vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,ex.getMessage(),null));
        }
        catch (Exception e){
            log.error("VendorController.deleteVendor():::Exception occurred while deleting vendor");
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
    }

}
