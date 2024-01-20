package com.organization.vehicleManager.processService;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BpmnService {

    private final TaskService taskService;

    public String getCurrentTaskId(String processInstanceId) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .active()
                .singleResult();
        if (task != null) {
            return task.getId();
        } else {
            throw new IllegalStateException("No active task found for the process instance ID: " + processInstanceId);
        }
    }
}