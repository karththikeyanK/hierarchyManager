package com.organization.vehicleManager.processdelegate;

import com.organization.vehicleManager.dto.OrganizationRequest;
import com.organization.vehicleManager.dto.OrganizationResponse;
import com.organization.vehicleManager.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component("oaProcessOrganizationDetailsDelegate")
public class oaProcessOrganizationDetailsDelegate implements JavaDelegate {

    private final OrganizationService organizationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try{
            OrganizationRequest organizationRequest = extractOrganizationRequest(execution);
            OrganizationResponse organizationResponse = organizationService.createOrganization(organizationRequest);
            execution.setVariable("organizationId", organizationResponse.getId());
            execution.setVariable("organizationCreateSuccessful", true);
        } catch(Exception e){
            log.error("Error while processing company details", e);
            execution.setVariable("organizationCreateSuccessful", false);
            throw e;
        }
    }

    private OrganizationRequest extractOrganizationRequest(DelegateExecution execution) {
        OrganizationRequest organizationRequest = (OrganizationRequest) execution.getVariable("organizationRequestDto");
        if (organizationRequest == null) {
            throw new BpmnError("NO_ORGANIZATION_DATA", "No organization data provided");
        }
        return organizationRequest;
    }
}
