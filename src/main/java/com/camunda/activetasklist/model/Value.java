package com.camunda.activetasklist.model;

public class Value {

	 private String type;
	    private String errorMessage;
	    private String errorCode;
	    public String getType() {
			return type;
		}
		@Override
		public String toString() {
			return "Value [type=" + type + ", errorMessage=" + errorMessage + ", errorCode=" + errorCode
					+ ", variables=" + variables + ", worker=" + worker + ", deadline=" + deadline
					+ ", processDefinitionVersion=" + processDefinitionVersion + ", elementInstanceKey="
					+ elementInstanceKey + ", processDefinitionKey=" + processDefinitionKey + ", processInstanceKey="
					+ processInstanceKey + ", bpmnProcessId=" + bpmnProcessId + ", retries=" + retries
					+ ", recurringTime=" + recurringTime + ", retryBackoff=" + retryBackoff + ", elementId=" + elementId
					+ ", customHeaders=" + customHeaders + "]";
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public Variables getVariables() {
			return variables;
		}
		public void setVariables(Variables variables) {
			this.variables = variables;
		}
		public String getWorker() {
			return worker;
		}
		public void setWorker(String worker) {
			this.worker = worker;
		}
		public int getDeadline() {
			return deadline;
		}
		public void setDeadline(int deadline) {
			this.deadline = deadline;
		}
		public int getProcessDefinitionVersion() {
			return processDefinitionVersion;
		}
		public void setProcessDefinitionVersion(int processDefinitionVersion) {
			this.processDefinitionVersion = processDefinitionVersion;
		}
		public long getElementInstanceKey() {
			return elementInstanceKey;
		}
		public void setElementInstanceKey(long elementInstanceKey) {
			this.elementInstanceKey = elementInstanceKey;
		}
		public long getProcessDefinitionKey() {
			return processDefinitionKey;
		}
		public void setProcessDefinitionKey(long processDefinitionKey) {
			this.processDefinitionKey = processDefinitionKey;
		}
		public long getProcessInstanceKey() {
			return processInstanceKey;
		}
		public void setProcessInstanceKey(long processInstanceKey) {
			this.processInstanceKey = processInstanceKey;
		}
		public String getBpmnProcessId() {
			return bpmnProcessId;
		}
		public void setBpmnProcessId(String bpmnProcessId) {
			this.bpmnProcessId = bpmnProcessId;
		}
		public int getRetries() {
			return retries;
		}
		public void setRetries(int retries) {
			this.retries = retries;
		}
		public int getRecurringTime() {
			return recurringTime;
		}
		public void setRecurringTime(int recurringTime) {
			this.recurringTime = recurringTime;
		}
		public int getRetryBackoff() {
			return retryBackoff;
		}
		public void setRetryBackoff(int retryBackoff) {
			this.retryBackoff = retryBackoff;
		}
		public String getElementId() {
			return elementId;
		}
		public void setElementId(String elementId) {
			this.elementId = elementId;
		}
		public CustomHeaders getCustomHeaders() {
			return customHeaders;
		}
		public void setCustomHeaders(CustomHeaders customHeaders) {
			this.customHeaders = customHeaders;
		}
		private Variables variables;
	    private String worker;
	    private int deadline;
	    private int processDefinitionVersion;
	    private long elementInstanceKey;
	    private long processDefinitionKey;
	    private long processInstanceKey;
	    private String bpmnProcessId;
	    private int retries;
	    private int recurringTime;
	    private int retryBackoff;
	    private String elementId;
	    private CustomHeaders customHeaders;
}
