package com.camunda.activetasklist.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.camunda.activetasklist.service.CamundaService;
import com.camunda.activetasklist.util.CamundaServiceUtility;


import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.Variable;

@Service
public class ServiceImplement implements CamundaService {
	
	@Autowired
	CamundaServiceUtility camundaServiceUtility;

	@Value("${camunda-env-SAAS}")
	private String camundaSAAS;

	@Value("${camunda-env-local}")
	private String camundaLocal;

	@Value("${selfmanaged-zeebe-record-Url}")
	private String selfmanagedzeeberecordUrls;

	@Value("${camunda-env-localelasticsearch}")
	private String localelasticsearchenv;

	final RestTemplate restTemplate = new RestTemplate();

	List<Task> tasks = null;

	
	// This is Utility Class :-
	
	
	
//	1. EnvByID :
	@Override
	public List getAllActTaskListByEnvID(String envID) {
		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {
			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				List<Variable> variables = iterate.getVariables();
				finalList.add(iterate);
			}
		}

		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				List<Variable> variables = iterate.getVariables();
				finalList.add(iterate);
			}
		}

		if (localelasticsearchenv.equalsIgnoreCase(envID)) {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Object getvalue = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				finalList.add(getSourceObj);

			}
		}

		return finalList;
	}

	// 2. getEnvIDwithPID
	@Override
	public List getEnvIDwithPID(String envID, String processInstanceID) {
		final List finalList = new ArrayList<>();

		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					String id = itrVar.getId();
					String[] str = id.split("-");
					String getID = str[0];
					if (processInstanceID.equals(getID)) {
						finalList.add(iterate);
					}
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					String id = itrVar.getId();
					String[] str = id.split("-");
					String getID = str[0];
					if (processInstanceID.equals(getID)) {
						finalList.add(iterate);
					}
				}
			}
		}
		List<Task> lst = new ArrayList<>();
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {

			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Map getsourceobj = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				long getProInstanceKey = (long) getValObj.get("processInstanceKey");
				String processInstanceKey = String.valueOf(getProInstanceKey);
				if (processInstanceID.equals(processInstanceKey)) {
					getsourceobj = (Map) getSource.get("_source");
				}
			}
			finalList.add(getsourceobj);
		}
		return finalList;
	}

	// 3. passingEnvIDwithWrkID
	@Override
	public List passingEnvIDwithWrkID(String envID, String workflowID) {

		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				String processName = iterate.getProcessName();
				if (workflowID.equals(processName)) {
					List<Variable> variables = iterate.getVariables();
					for (Variable itrVar : variables) {
						String id = itrVar.getId();
						String[] str = id.split("-");
						String getID = str[0];
						finalList.add(iterate);
					}
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				String processName = iterate.getProcessName();
				if (workflowID.equals(processName)) {
					List<Variable> variables = iterate.getVariables();
					for (Variable itrVar : variables) {
						String id = itrVar.getId();
						String[] str = id.split("-");
						String getID = str[0];
						finalList.add(iterate);
					}
				}
			}
		}
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {

			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				String bpmnProcessId = (String) getValObj.get("bpmnProcessId");
				if (workflowID.equals(bpmnProcessId)) {
					Map getvalue = (Map) getSourceObj.get("value");
					finalList.add(getvalue);
				}
			}
		}
		return finalList;
	}

	// 4 : passingEnvIDwithDefID
	@Override
	public List passingEnvIDwithDefID(String envID, String processDefID) {

		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}

			for (Task iterate : tasks) {
				String processDefIDObj = iterate.getProcessDefinitionId();
				if (processDefID.equals(processDefIDObj)) {
					finalList.add(iterate);
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String processDefIDObj = iterate.getProcessDefinitionId();
				if (processDefID.equals(processDefIDObj)) {
					finalList.add(iterate);
				}
			}
		}
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {

			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				long processDefinitionKeyObj = (long) getValObj.get("processDefinitionKey");

				String processDefinitionKey = String.valueOf(processDefinitionKeyObj);
				if (processDefID.equals(processDefinitionKey)) {
					Map getvalue = (Map) getSourceObj.get("value");
					finalList.add(getvalue);
				}
			}
		}
		return finalList;
	}

	// 5. passingEnvIDwithTaskID:
	@Override
	public List passingEnvIDwithTaskID(String envID, String taskID) {

		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String getTaskId = iterate.getId();
				if (taskID.equals(getTaskId)) {
					finalList.add(iterate);
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String getTaskId = iterate.getId();
				if (taskID.equals(getTaskId)) {
					finalList.add(iterate);
				}
			}
		}
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {

			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Map getvalue = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				long key = (long) getSourceObj.get("key");
				String getKey = String.valueOf(key);
				if (taskID.equals(getKey)) {
					getvalue = (Map) getSourceObj.get("value");
					Map getSourc = (Map) getSource.get("_source");
					finalList.add(getSourc);
				}
			}
		}
		return finalList;
	}

//	6. passing EnvIDassignwithPID
	@Override
	public List passingEnvIDassignwithPID(String envID, String assignee, String processInstanceID) {
		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					String id = itrVar.getId();
					String[] str = id.split("-");
					String getID = str[0];
					if (assignee.equals(assigneeObj)) {
						if (processInstanceID.equals(getID)) {
							finalList.add(iterate);
						}
					}
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					String id = itrVar.getId();
					String[] str = id.split("-");
					String getID = str[0];
					if (assignee.equals(assigneeObj)) {
						if (processInstanceID.equals(getID)) {
							finalList.add(iterate);
						}

					}
				}
			}
		}
		List<Task> lst = new ArrayList<>();
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {

			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Map getsourceobj = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				Map customHeadersObj = (Map) getValObj.get("customHeaders");
				String assigneeObj = (String) customHeadersObj.get("io.camunda.zeebe:assignee");
				long getProInstanceKey = (long) getValObj.get("processInstanceKey");
				String processInstanceKey = String.valueOf(getProInstanceKey);
				if (assignee.equals(assigneeObj)) {
					if (processInstanceID.equals(processInstanceKey)) {
						getsourceobj = (Map) getSource.get("_source");
						finalList.add(getsourceobj);
					}
				}
			}

		}
		return finalList;
	}

//	7. passingEnvIDassigPFK
	@Override
	public List passingEnvIDassigPFK(String envID, String assignee, String processDefinitionKey) {

		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				String processDefinitionId = iterate.getProcessDefinitionId();
				if (assignee.equals(assigneeObj)) {
					if (processDefinitionKey.equals(processDefinitionId)) {
						finalList.add(iterate);
					}
				}
			}
		}
		if (camundaLocal.equals(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				String processDefinitionId = iterate.getProcessDefinitionId();
				if (assignee.equals(assigneeObj)) {
					if (processDefinitionKey.equals(processDefinitionId)) {
						finalList.add(iterate);
					}

				}
			}
		}
		List<Task> lst = new ArrayList<>();
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Map getsourceobj = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				Map customHeadersObj = (Map) getValObj.get("customHeaders");
				String assigneeObj = (String) customHeadersObj.get("io.camunda.zeebe:assignee");
				long processDefinition = (long) getValObj.get("processDefinitionKey");
				String getProcessDefinitionKey = String.valueOf(processDefinition);
				if (assignee.equals(assigneeObj)) {
					if (processDefinitionKey.equals(getProcessDefinitionKey)) {
						getsourceobj = (Map) getSource.get("_source");
						finalList.add(getsourceobj);
					}
				}
			}

		}
		return finalList;
	}

//	8. EnvIDwithAssign
	@Override
	public List EnvIDwithAssign(String envID, String assignee) {
		final List finalList = new ArrayList<>();
		if (camundaSAAS.equalsIgnoreCase(envID)) {

			try {
				tasks = camundaServiceUtility.getTaskSaasAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				String processDefinitionId = iterate.getProcessDefinitionId();
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					if (assignee.equals(assigneeObj)) {
						finalList.add(iterate);
					}
				}
			}
		}
		if (camundaLocal.equals(envID)) {
			ServiceImplement camundaServiceImp = new ServiceImplement();
			try {

				List<Task> tasks = camundaServiceUtility.getTaskSimpleAuthentication();
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Task iterate : tasks) {
				String assigneeObj = iterate.getAssignee();
				String processDefinitionId = iterate.getProcessDefinitionId();
				List<Variable> variables = iterate.getVariables();
				for (Variable itrVar : variables) {
					if (assignee.equals(assigneeObj)) {
						finalList.add(iterate);
					}
				}
			}
		}
		List<Task> lst = new ArrayList<>();
		if (localelasticsearchenv.equalsIgnoreCase(envID)) {
			ResponseEntity<Map> responseEntity = restTemplate.exchange(selfmanagedzeeberecordUrls, HttpMethod.POST,
					null, Map.class);
			Map resBody = responseEntity.getBody();
			Map object = (Map) resBody.get("hits");
			List getHitsList = (List) object.get("hits");
			Map getsourceobj = null;
			for (Object itr : getHitsList) {
				Map getSource = (Map) itr;
				Map getSourceObj = (Map) getSource.get("_source");
				Map getValObj = (Map) getSourceObj.get("value");
				Map customHeadersObj = (Map) getValObj.get("customHeaders");
				String assigneeObj = (String) customHeadersObj.get("io.camunda.zeebe:assignee");
				long processDefinitionKey = (long) getValObj.get("processDefinitionKey");
				String getProcessDefinitionKey = String.valueOf(processDefinitionKey);
				if (assignee.equals(assigneeObj)) {
					getsourceobj = (Map) getSource.get("_source");
					finalList.add(getsourceobj);
				}
			}

		}
		return finalList;

	}

}
