package com.organization.vehicleManager.controller;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.response.ApiResponse;
import com.organization.vehicleManager.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
       try {
              return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS, "Customer created successfully", customerService.createCustomer(customerRequest)));
       }catch (GeneralBusinessException e) {
           log.error("Customer Controller::Error while creating customer", e);
           return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       } catch (Exception e) {
              log.error("Customer Controller::Error while creating customer", e);
              return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable Integer id) {
       try {
              return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS, "Customer fetched successfully", customerService.getCustomer(id)));
       }catch (GeneralBusinessException e) {
           log.error("Customer Controller::Error while fetching customer", e);
           return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       } catch (Exception e) {
              log.error("Customer Controller::Error while fetching customer", e);
              return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllCustomers() {
       try {
              return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS, "Customers fetched successfully", customerService.getAllCustomers()));
       }catch (GeneralBusinessException e) {
           log.error("Customer Controller::Error while fetching customers", e);
           return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       } catch (Exception e) {
              log.error("Customer Controller::Error while fetching customers", e);
              return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Integer id, @RequestBody CustomerRequest customerRequest) {
       try {
              return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS, "Customer updated successfully", customerService.updateCustomer(id, customerRequest)));
       }catch (GeneralBusinessException e) {
           log.error("Customer Controller::Error while updating customer", e);
           return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       } catch (Exception e) {
              log.error("Customer Controller::Error while updating customer", e);
              return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Integer id) {
       try {
              return ResponseEntity.ok().body(new ApiResponse(ApiResponse.SUCCESS, "Customer deleted successfully", customerService.deleteCustomer(id)));
       }catch (GeneralBusinessException e) {
           log.error("Customer Controller::Error while deleting customer", e);
           return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       } catch (Exception e) {
              log.error("Customer Controller::Error while deleting customer", e);
              return ResponseEntity.badRequest().body(new ApiResponse(ApiResponse.ERROR, e.getMessage(), null));
       }
    }
}
