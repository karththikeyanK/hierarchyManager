package com.organization.vehicleManager.processDefinition;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessDefinitionConfig {

    private final RepositoryService repositoryService;

    @PostConstruct
    public void createProcessDefinition() {
        BpmnModelInstance expresswayPaymentModel = createOrganizationAddingProcess();
        repositoryService.createDeployment()
                .addModelInstance("organizationProcess.bpmn", expresswayPaymentModel)
                .deploy();
    }


    private BpmnModelInstance createOrganizationAddingProcess() {
        return Bpmn.createExecutableProcess("OrganizationAddingData")
                .camundaHistoryTimeToLive(30)
                .startEvent("startAddingData")

                .userTask("EnterOrganizationData").name("Enter Organization Details")
                .serviceTask("ProcessOrganizationDetails").name("Process Organization Details")
                .camundaDelegateExpression("${oaProcessOrganizationDetailsDelegate}")

                .userTask("EnterCompanyDetails").name("Enter Company Details")
                .serviceTask("ProcessCompanyDetails").name("Process Company Details")
                .camundaDelegateExpression("${oaProcessCompanyDetailsDelegate}")

                .userTask("EnterCustomerDetails").name("Enter Customer Details")
                .serviceTask("ProcessCustomerDetails").name("Process Customer Details")
                .camundaDelegateExpression("${oaProcessCustomerDetailsDelegate}")

                .endEvent("AddingOrganizationDataComplete")
                .done();
    }
}
