package com.organization.vehicleManager.processcontroller;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.ErrorDto;
import com.organization.vehicleManager.processService.BpmnService;
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

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/againCustomer")
public class oaProcessAgainCustomerDetailsController {
    private final BpmnService bpmnService;
    private final TaskService taskService;


    @PostMapping("/againEnterCustomerDetails/{taskId}")
    public ResponseEntity<ApiResponse<String>> againEnterCustomerDetails(@PathVariable String taskId, @RequestBody CustomerRequest customerRequest) {
        log.info("oaProcessCustomerDetailsController:EnterCustomerDetails():: is called");
        try {
            Map<String, Object> variables = new HashMap<>();
            variables.put("customerAgainRequestDto", customerRequest);
            taskService.complete(taskId, variables);
            ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                    .status("success")
                    .data("Enter Customer Details again task completed successfully.")
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (Exception ex) {
            return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(Exception ex, HttpStatus status) {
        List<ErrorDto> errors = Collections.singletonList(new ErrorDto(null, ex.getMessage()));
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .status("error")
                .msg(errors.toString())
                .build();
        return new ResponseEntity<>(apiResponse, status);
    }


}
