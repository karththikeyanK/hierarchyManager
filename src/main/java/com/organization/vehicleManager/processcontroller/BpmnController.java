package com.organization.vehicleManager.processcontroller;

import com.organization.vehicleManager.dto.ErrorDto;
import com.organization.vehicleManager.processService.BpmnService;
import com.organization.vehicleManager.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BpmnController {

    private final BpmnService bpmnService;

    @PostMapping("api/v1/auth/currentTask")
    public ResponseEntity<ApiResponse<String>> getCurrentTaskId(@RequestBody Map<String, String> requestBody) {
        try {
            String processInstanceId = requestBody.get("processInstanceId");
            String taskId = bpmnService.getCurrentTaskId(processInstanceId);
            ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                    .status("success")
                    .data(taskId)
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