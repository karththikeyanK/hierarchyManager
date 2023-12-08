package com.organization.vehicleManager.processcontroller;

import com.organization.vehicleManager.dto.CompanyRequest;
import com.organization.vehicleManager.dto.ErrorDto;
import com.organization.vehicleManager.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/company")
@Slf4j
@RequiredArgsConstructor
public class oaProcessCompanyDetailsController {
    private final TaskService taskService;

    @PostMapping("/completeEnterCompanyDetails/{taskId}")
    public ResponseEntity<ApiResponse<String>> EnterCompanyDetails(@PathVariable String taskId, @RequestBody CompanyRequest companyRequest) {
        try {
            log.info("oaProcessCompanyDetailsController:EnterCompanyDetails():: is called with taskId: "+ taskId);
            Map<String, Object> variables = new HashMap<>();
            variables.put("companyRequestDto", companyRequest);
            taskService.complete(taskId, variables);
            log.info("oaProcessCompanyDetailsController:completeEnterCompanyDetails():: Task completed successfully");
            ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                    .status("success")
                    .data("Enter Company Details task completed successfully.")
                    .build();
            return ResponseEntity.ok(apiResponse);
        } catch (BpmnError ex) {
            return buildBpmnErrorResponse(ex);
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


    private <T> ResponseEntity<ApiResponse<T>> buildBpmnErrorResponse(Exception ex) {
        List<ErrorDto> errors = Collections.singletonList(new ErrorDto(null, ex.getMessage()));
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .status("error")
                .msg(errors.toString())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
