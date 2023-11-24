package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.RegionRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createRegion(@RequestBody RegionRequest regionRequest) {
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS, "Region created successfully", regionService.createRegion(regionRequest)));
        }catch (GeneralBusinessException e) {
            log.error("Error creating region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e) {
            log.error("Error creating region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateRegion(@PathVariable Integer id, @RequestBody RegionRequest regionRequest) {
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS, "Region updated successfully", regionService.updateRegion(id, regionRequest)));
        }catch (GeneralBusinessException e) {
            log.error("Error updating region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e) {
            log.error("Error updating region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getRegion(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS, "Region fetched successfully", regionService.getRegion(id)));
        }catch (GeneralBusinessException e) {
            log.error("Error fetching region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e) {
            log.error("Error fetching region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllRegions() {
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS, "Regions fetched successfully", regionService.getAllRegions()));
        }catch (GeneralBusinessException e) {
            log.error("Error fetching regions", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e) {
            log.error("Error fetching regions", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteRegion(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS, "Region deleted successfully", regionService.deleteRegion(id)));
        }catch (GeneralBusinessException e) {
            log.error("Error deleting region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
        catch (Exception e) {
            log.error("Error deleting region", e);
            return ResponseEntity.ok(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
        }
    }

}
