package com.camunda.activetasklist.service;

import java.util.List;

import io.camunda.tasklist.dto.Task;


public interface CamundaService {
	


	List<Task> getAllActTaskListByEnvID(String envID);

	List<Task> getEnvIDwithPID(String envID, String processInstanceID);

	List<Task> passingEnvIDwithWrkID(String envID, String workflowID);

	List<Task> passingEnvIDwithDefID(String envID, String processDefID);

	List<Task> passingEnvIDwithTaskID(String envID, String taskID);

	List<Task> passingEnvIDassignwithPID(String envID, String assignee, String processInstanceID);

	List<Task> passingEnvIDassigPFK(String envID, String assignee, String processDefinitionKey);

	List<Task> EnvIDwithAssign(String envID, String assignee);

}
