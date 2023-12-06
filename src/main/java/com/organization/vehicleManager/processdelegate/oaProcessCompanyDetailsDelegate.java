package com.organization.vehicleManager.processdelegate;

import com.organization.vehicleManager.dto.CompanyRequest;
import com.organization.vehicleManager.dto.CompanyResponse;
import com.organization.vehicleManager.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("oaProcessCompanyDetailsDelegate")
public class oaProcessCompanyDetailsDelegate implements JavaDelegate {

  private final CompanyService companyService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try{
            CompanyRequest companyRequest = extractCompanyRequest(execution);
            CompanyResponse companyResponse = companyService.create(companyRequest);
            execution.setVariable("companyId", companyResponse.getId());
            execution.setVariable("companyAddedSuccessful", true);
        } catch(Exception e){
            log.error("Error while processing company details", e);
            execution.setVariable("companyAddedSuccessful", false);
            throw e;
        }
    }

    private CompanyRequest extractCompanyRequest(DelegateExecution execution) {
        CompanyRequest companyRequest = (CompanyRequest) execution.getVariable("companyRequestDto");
        if (companyRequest == null) {
            throw new BpmnError("NO_COMPANY_DATA", "No company data provided");
        }
        return companyRequest;
    }

}
