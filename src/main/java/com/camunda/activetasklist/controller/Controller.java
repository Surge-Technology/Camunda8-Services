package com.camunda.activetasklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.activetasklist.service.CamundaService;

import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;

@RestController
public class Controller {
// test API :
	@GetMapping("/demo")
	public String demo() {
		return "okay";
	}

	@Autowired
	private CamundaService camundaService;

//	1. passingEnvID :
	@GetMapping("/getALLActTaskListByEnv/{envID}")
	public List<Task> getALLActTaskListByEnv(@PathVariable String envID) throws Exception {
		List<Task> allActTaskListByEnvID = camundaService.getAllActTaskListByEnvID(envID);
		return allActTaskListByEnvID;
	}

	// 2. passingEnvID_ProcessInstanceID
	@GetMapping("/getActTaskListByEnvId/{envID}/{processInstanceID}")
	public List<Task> getActTaskListByProcessInstanceID(@PathVariable String envID,
			@PathVariable String processInstanceID) throws Exception {
		 List<Task> envIDwithPID = camundaService.getEnvIDwithPID(envID, processInstanceID);
		 return envIDwithPID;
	}

	// 3. passingEnvID_WrkID
	@GetMapping("/getActTaskList/{envID}/{workflowID}")
	public List<Task> getActTaskListByEnvIDWorkflowIDProcessInstanceID(@PathVariable String envID,
			@PathVariable String workflowID) throws Exception {
		return camundaService.passingEnvIDwithWrkID(envID, workflowID);
	}

	// 4 : passingEnvID_ProcessDefinitionID
	@GetMapping("/getActTaskListProDefID/{envID}/{processDefID}")
	public List<Task> getActTaskListByProDefID(@PathVariable String envID, @PathVariable String processDefID)
			throws Exception {
		List<Task> passingEnvIDwithDefID = camundaService.passingEnvIDwithDefID(envID, processDefID);
		return passingEnvIDwithDefID;
	}

	// 5. passingEnvID_TaskID
	@GetMapping("/getActTaskListTaskID/{envID}/{taskID}")
	public List<Task> getActTaskListByEnvIDTaskID(@PathVariable String envID, @PathVariable String taskID)
			throws Exception {
		List<Task> passingEnvIDwithTaskID = camundaService.passingEnvIDwithTaskID(envID, taskID);
		return passingEnvIDwithTaskID;

	}

//	6. passing EnvID_AssigneeName_ProcessInstance_ID
	@GetMapping("/getActTaskListByassignee/{envID}/{assignee}/{processInstanceID}")
	public List<Task> getActTaskListByassignee(@PathVariable String envID, @PathVariable String assignee,
			@PathVariable String processInstanceID) throws Exception {
		List<Task> passingEnvIDassignwithPID = camundaService.passingEnvIDassignwithPID(envID, assignee, processInstanceID);
		return passingEnvIDassignwithPID;
	}

//	7. passingEnvID_AssigneeName_ProcessDefinitionKey
	@GetMapping("/getActTaskListByWKFidasign/{envID}/{assignee}/{processDefinitionKey}")
	public List<Task> getActTaskListByWKFidasign(@PathVariable String envID, @PathVariable String assignee,
			@PathVariable String processDefinitionKey) throws Exception {
		List<Task> passingEnvIDassigPFK = camundaService.passingEnvIDassigPFK(envID, assignee, processDefinitionKey);
		return passingEnvIDassigPFK;
	}

//	8. passingEnvID_assigneeName::
	@GetMapping("/getActTaskListByasign/{envID}/{assignee}")
	public List<Task> getActTaskListByasign(@PathVariable String envID, @PathVariable String assignee)
			throws Exception {
		List<Task> envIDwithAssign = camundaService.EnvIDwithAssign(envID, assignee);
		return envIDwithAssign;
	}

//	9. passingVariableName : getTaskDetails
	@GetMapping("/getALLActiveTaskList/{envirnment}/{varName}")
	public List<Task> getALLActiveTaskListEnvironment(@PathVariable String envirnment, @PathVariable String varName)
			throws TaskListException {
		List<Task> passingVarNameGetActiveTaskID = camundaService.passingVarNameGetActiveTaskID(envirnment, varName);
		return passingVarNameGetActiveTaskID;
	}

}
