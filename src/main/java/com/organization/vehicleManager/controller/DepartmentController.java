package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.DepartmentRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS,"Department created successfully",departmentService.createDepartment(departmentRequest)));
        }catch (GeneralBusinessException e){
            log.error("General Business Exception:Error while creating department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
        catch (Exception e){
            log.error("Unexpected error while creating department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateDepartment(@PathVariable Integer id,@RequestBody DepartmentRequest departmentRequest){
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS,"Department updated successfully",departmentService.updateDepartment(id,departmentRequest)));
        }catch (GeneralBusinessException e){
            log.error("General Business Exception:Error while updating department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
        catch (Exception e){
            log.error("Unexpected error while updating department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getDepartment(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS,"Department fetched successfully",departmentService.getDepartment(id)));
        }catch (GeneralBusinessException e){
            log.error("General Business Exception:Error while getting department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
        catch (Exception e){
            log.error("Unexpected error while getting department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllDepartment(){
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS,"All departments fetched successfully",departmentService.getAllDepartment()));
        }catch (GeneralBusinessException e){
            log.error("General Business Exception:Error while getting all departments: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
        catch (Exception e){
            log.error("Unexpected error while getting all departments: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(new ApiResponse(ApiResponse.SUCCESS,"Department deleted successfully",departmentService.deleteDepartment(id)));
        }catch (GeneralBusinessException e){
            log.error("General Business Exception:Error while deleting department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }
        catch (Exception e){
            log.error("Unexpected error while deleting department: ", e);
            return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR,e.getMessage(),null));
        }

    }

}
