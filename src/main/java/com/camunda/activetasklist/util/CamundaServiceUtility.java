package com.camunda.activetasklist.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SaasAuthentication;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;

@Component
public class CamundaServiceUtility {



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

	SaasAuthentication saasAuthentication = null;
	SimpleAuthentication simpleAuthentication = null;
	CamundaTaskListClient client = null;
	List<Task> tasks = null;

	public List getTaskSaasAuthentication() throws Exception {
		saasAuthentication = new SaasAuthentication(clientId, clientSecret);
		client = new CamundaTaskListClient.Builder().taskListUrl(taskListUrls).shouldReturnVariables()
				.authentication(saasAuthentication).build();
		return tasks = client.getTasks(true, TaskState.CREATED, 50, true); 
	}

	public List getTaskSimpleAuthentication() throws Exception {
		simpleAuthentication = new SimpleAuthentication(selfmanagedClientId, selfmanagedClientSecret);

		client = new CamundaTaskListClient.Builder().taskListUrl(selfmanagedtaskListUrls).shouldReturnVariables()
				.authentication(simpleAuthentication).build();

		return tasks = client.getTasks(true, TaskState.CREATED, 50, true);
	}	
}
