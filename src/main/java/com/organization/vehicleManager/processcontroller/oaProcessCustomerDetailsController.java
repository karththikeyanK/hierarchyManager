package com.organization.vehicleManager.processcontroller;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.ErrorDto;
import com.organization.vehicleManager.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class oaProcessCustomerDetailsController {

    private final TaskService taskService;
    @PostMapping("/completeEnterCustomerDetails/{taskId}")
    public ResponseEntity<ApiResponse<String>> completeEnterCustomerDetails(@PathVariable String taskId, @RequestBody CustomerRequest customerRequest) {
        log.info("oaProcessCustomerDetailsController:EnterCustomerDetails():: is called");
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerRequestDto", customerRequest);
            taskService.complete(taskId, variables);
            ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                    .status("success")
                    .data("Enter Customer Details task completed successfully.")
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private <T> ResponseEntity<com.organization.vehicleManager.response.ApiResponse<T>> buildErrorResponse(Exception ex, HttpStatus status) {
        List<ErrorDto> errors = Collections.singletonList(new ErrorDto(null, ex.getMessage()));
        com.organization.vehicleManager.response.ApiResponse<T> apiResponse = com.organization.vehicleManager.response.ApiResponse.<T>builder()
                .status("error")
                .msg(errors.toString())
                .build();
        return new ResponseEntity<>(apiResponse, status);
    }



}
