package com.organization.vehicleManager.processdelegate;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.CustomerResponse;
import com.organization.vehicleManager.dto.OrganizationRequest;
import com.organization.vehicleManager.dto.OrganizationResponse;
import com.organization.vehicleManager.service.CustomerService;
import com.organization.vehicleManager.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("oaProcessCustomerDetailsDelegate")
public class oaProcessCustomerDetailsDelegate implements JavaDelegate {

    private final CustomerService customerService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try{
            CustomerRequest customerRequest = extractOrganizationRequest(execution);
            CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
            execution.setVariable("organizationId", customerResponse.getId());
            execution.setVariable("customerCreateSuccessful", true);
        } catch(Exception e){
            log.error("Error while processing company details", e);
            execution.setVariable("customerCreateSuccessful", false);
            throw e;
        }
    }

    private CustomerRequest extractOrganizationRequest(DelegateExecution execution) {
        CustomerRequest customerRequest = (CustomerRequest) execution.getVariable("customerRequestDto");
        if (customerRequest == null) {
            throw new BpmnError("NO_CUSTOMER_DATA", "No customer data provided");
        }
        return customerRequest;
    }

}
