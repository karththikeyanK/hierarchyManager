package com.organization.vehicleManager.processdelegate;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.CustomerResponse;
import com.organization.vehicleManager.entity.Customer;
import com.organization.vehicleManager.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("oaProcessAgainCustomerDetailsDelegate")
public class oaProcessCustomerDetailsAgainDelegate  implements JavaDelegate {

    private final CustomerService customerService;
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try{
            log.info("oaProcessCustomerDetailsDelegate:execute():: is called");
            CustomerRequest customerRequest = extractCustomerRequest(execution);
            customerRequest.setReason("Customer details are not verified");
            CustomerResponse customer = customerService.getCustomerEntityByContactNumber(customerRequest.getContactNo());
            CustomerResponse customerResponse = customerService.updateCustomer(customer.getId(),customerRequest);
            execution.setVariable("customerId", customerResponse.getId());
            execution.setVariable("customerCreateSuccessful", true);
            execution.setVariable("verifiedStatus", "success");

        } catch(Exception e){
            log.error("Error while processing company details", e);
            execution.setVariable("customerCreateSuccessful", false);
            throw e;
        }
    }

    private CustomerRequest extractCustomerRequest(DelegateExecution execution) {
        CustomerRequest customerRequest = (CustomerRequest) execution.getVariable("customerAgainRequestDto");
        if (customerRequest == null) {
            throw new BpmnError("NO_CUSTOMER_DATA", "No customer data provided");
        }
        return customerRequest;
    }
}
