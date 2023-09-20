package com.camunda.activetasklist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.camunda.activetasklist.service.CamundaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SaasAuthentication;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;

@RestController
public class Controller {

	@Autowired
	private CamundaService camundaService;

	@Value("${camunda-env-SAAS}")
	private String camundaSAAS;

	@Value("${camunda-env-local}")
	private String camundaLocal;

	@Value("${zeebe.client.cloud.clientId}")
	private String clientId;

	@Value("${zeebe.client.cloud.clientSecret}")
	private String clientSecret;

	@Value("${camunda-saas-taskList-Url}")
	private String taskListUrls;

	@Value("${selfmanaged.clientId}")
	private String selfmanagedClientId;

	@Value("${selfmanaged.clientSecret}")
	private String selfmanagedClientSecret;

	@Value("${selfmanaged-taskList-Url}")
	private String selfmanagedtaskListUrls;

	@Value("${selfmanaged-zeebe-record-Url}")
	private String selfmanagedzeeberecordUrls;

	@Value("${camunda-env-localelasticsearch}")
	private String localelasticsearchenv;

	final RestTemplate restTemplate = new RestTemplate();

	List<Task> tasks = null;
	SaasAuthentication saasAuthentication = null;
	SimpleAuthentication simpleAuthentication = null;
	CamundaTaskListClient client = null;

	@GetMapping("/getALLActiveTaskList")
	public List<Task> getALLActiveTaskList() throws TaskListException {

		saasAuthentication = new SaasAuthentication(clientId, clientSecret);

		client = new CamundaTaskListClient.Builder().taskListUrl(taskListUrls).shouldReturnVariables()
				.authentication(saasAuthentication).build();

		return client.getTasks(true, TaskState.CREATED, 50, true);
	}

	@GetMapping("/getALLActiveTaskLists/{envirnment}")
	public List<Task> getALLActiveTaskListEnvironment(@PathVariable String envirnment) throws TaskListException {
		if (envirnment.equals(camundaSAAS)) {
			saasAuthentication = new SaasAuthentication(clientId, clientSecret);
			client = new CamundaTaskListClient.Builder().taskListUrl(taskListUrls).shouldReturnVariables()
					.authentication(saasAuthentication).build();
			tasks = client.getTasks(true, TaskState.CREATED, 50, true);
		}
		return tasks;

	}

	@GetMapping("/getALLActiveTaskListSelfManaged")
	public List<Task> getALLActiveTaskListSelfManaged() throws TaskListException {
		simpleAuthentication = new SimpleAuthentication(selfmanagedClientId, selfmanagedClientSecret);
		client = new CamundaTaskListClient.Builder().taskListUrl(selfmanagedtaskListUrls).shouldReturnVariables()
				.authentication(simpleAuthentication).build();
		return client.getTasks(true, TaskState.CREATED, 50, true);
	}

	@GetMapping("/getALLActiveTaskListSelfManageds/{envirnment}")
	public List<Task> getALLActiveTaskListSelfManageds(@PathVariable String envirnment) throws TaskListException {

		if (envirnment.equals(camundaLocal)) {
			simpleAuthentication = new SimpleAuthentication(selfmanagedClientId, selfmanagedClientSecret);

			client = new CamundaTaskListClient.Builder().taskListUrl(selfmanagedtaskListUrls).shouldReturnVariables()
					.authentication(simpleAuthentication).build();
			tasks = client.getTasks(true, TaskState.CREATED, 50, true);
		}
		return tasks;
	}

	@GetMapping("/getActTaskByProInsID/{processInstanceID}")
	public List getActiveTaskListByProcessInstanceID(@PathVariable String processInstanceID)
			throws JsonMappingException, JsonProcessingException {
		final List finalList = new ArrayList<>();

		ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST, null,
				Map.class);

		Map resBody = responseEntity.getBody();
		Map getHits = (Map) resBody.get("hits");
		List getHitsList = (List) getHits.get("hits");
		Object getsourceobj = null;
		for (Object itrHits : getHitsList) {
			Map getMapObj = (Map) itrHits;
			Map getSource = (Map) getMapObj.get("_source");
			Object getvalueObj = getSource.get("value");
			Map getvalueMapObj = (Map) getvalueObj;
			long getprocessInstanceKey = (long) getvalueMapObj.get("processInstanceKey");
			String id = String.valueOf(getprocessInstanceKey);
			if (processInstanceID.equals(id)) {
				getsourceobj = getMapObj.get("_source");
				finalList.add(getsourceobj);
			}
		}
		return finalList;
	}

//	1. EnvByID :
	@GetMapping("/getALLActTaskListByEnv/{envID}")
	public List<Task> getALLActTaskListByEnv(@PathVariable String envID) throws Exception {
		return camundaService.getAllActTaskListByEnvID(envID);
	}

	// 2. getEnvIDwithPID
	@GetMapping("/getActTaskListByEnvId/{envID}/{processInstanceID}")
	public List<Task> getActTaskListByProcessInstanceID(@PathVariable String envID,
			@PathVariable String processInstanceID) throws Exception {
		return camundaService.getEnvIDwithPID(envID, processInstanceID);
	}

	// this 1 is for processName BPMN Name workflowID :

	// 3. passingEnvIDwithWrkID
	@GetMapping("/getActTaskList/{envID}/{workflowID}")
	public List<Task> getActTaskListByEnvIDWorkflowIDProcessInstanceID(@PathVariable String envID,
			@PathVariable String workflowID) throws Exception {
		return camundaService.passingEnvIDwithWrkID(envID, workflowID);
	}

	// this 1 is for Process Definition key :-\

	// 4 : passingEnvIDwithDefID
	@GetMapping("/getActTaskListProDefID/{envID}/{processDefID}")
	public List<Task> getActTaskListByProDefID(@PathVariable String envID, @PathVariable String processDefID)
			throws Exception {
		return camundaService.passingEnvIDwithDefID(envID, processDefID);
	}

	// 5. passingEnvIDwithTaskID
	@GetMapping("/getActTaskListTaskID/{envID}/{taskID}")
	public List<Task> getActTaskListByEnvIDTaskID(@PathVariable String envID, @PathVariable String taskID)
			throws Exception {
		return camundaService.passingEnvIDwithTaskID(envID, taskID);

	}

	// sep 15, 2023 :

	// Another Extra API :

//	EnvID_processInstanceID _withassignee:

//	6. passing EnvIDassignwithPID
	@GetMapping("/getActTaskListByassignee/{envID}/{assignee}/{processInstanceID}")
	public List<Task> getActTaskListByassignee(@PathVariable String envID, @PathVariable String assignee,
			@PathVariable String processInstanceID) throws Exception {
		return camundaService.passingEnvIDassignwithPID(envID, assignee, processInstanceID);
	}

	//

//	EnvID_workflowID_with assignee::

//	7. passingEnvIDassigPFK
	@GetMapping("/getActTaskListByWKFidasign/{envID}/{assignee}/{processDefinitionKey}")
	public List<Task> getActTaskListByWKFidasign(@PathVariable String envID, @PathVariable String assignee,
			@PathVariable String processDefinitionKey) throws Exception {
		return camundaService.passingEnvIDassigPFK(envID, assignee, processDefinitionKey);
	}

	// GetEnvID_Assignee :

//	EnvID_workflowID_with assignee::

//	8. EnvIDwithAssign
	@GetMapping("/getActTaskListByasign/{envID}/{assignee}")
	public List<Task> getActTaskListByasign(@PathVariable String envID, @PathVariable String assignee)
			throws Exception {
		return camundaService.EnvIDwithAssign(envID, assignee);
	}

}
