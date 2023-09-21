package com.camunda.activetasklist.model;

public class Source {

	private String id;
	private int partitionId;
	private Value value;
	private long key;
	private long timestamp;

	private String valueType;
	private int sourceRecordPosition;
	private String recordType;
	private String intent;
	private String rejectionType;
	private String rejectionReason;
	private String brokerVersion;

	// Additional :-
	private String bpmnProcessId;
	private String processDefinitionId;
	private String flowNodeBpmnId;
	private String flowNodeInstanceId;
	private String processInstanceId;
	private String creationTime;
	private String completionTime;
	private String state;
	private String assignee;
	private String candidateGroups;
	private String formKey;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPartitionId() {
		return partitionId;
	}
	public void setPartitionId(int partitionId) {
		this.partitionId = partitionId;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public int getSourceRecordPosition() {
		return sourceRecordPosition;
	}
	public void setSourceRecordPosition(int sourceRecordPosition) {
		this.sourceRecordPosition = sourceRecordPosition;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getRejectionType() {
		return rejectionType;
	}
	public void setRejectionType(String rejectionType) {
		this.rejectionType = rejectionType;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getBrokerVersion() {
		return brokerVersion;
	}
	public void setBrokerVersion(String brokerVersion) {
		this.brokerVersion = brokerVersion;
	}
	public String getBpmnProcessId() {
		return bpmnProcessId;
	}
	public void setBpmnProcessId(String bpmnProcessId) {
		this.bpmnProcessId = bpmnProcessId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getFlowNodeBpmnId() {
		return flowNodeBpmnId;
	}
	public void setFlowNodeBpmnId(String flowNodeBpmnId) {
		this.flowNodeBpmnId = flowNodeBpmnId;
	}
	public String getFlowNodeInstanceId() {
		return flowNodeInstanceId;
	}
	public void setFlowNodeInstanceId(String flowNodeInstanceId) {
		this.flowNodeInstanceId = flowNodeInstanceId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getCandidateGroups() {
		return candidateGroups;
	}
	public void setCandidateGroups(String candidateGroups) {
		this.candidateGroups = candidateGroups;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	@Override
	public String toString() {
		return "Source [id=" + id + ", partitionId=" + partitionId + ", value=" + value + ", key=" + key
				+ ", timestamp=" + timestamp + ", valueType=" + valueType + ", sourceRecordPosition="
				+ sourceRecordPosition + ", recordType=" + recordType + ", intent=" + intent + ", rejectionType="
				+ rejectionType + ", rejectionReason=" + rejectionReason + ", brokerVersion=" + brokerVersion
				+ ", bpmnProcessId=" + bpmnProcessId + ", processDefinitionId=" + processDefinitionId
				+ ", flowNodeBpmnId=" + flowNodeBpmnId + ", flowNodeInstanceId=" + flowNodeInstanceId
				+ ", processInstanceId=" + processInstanceId + ", creationTime=" + creationTime + ", completionTime="
				+ completionTime + ", state=" + state + ", assignee=" + assignee + ", candidateGroups="
				+ candidateGroups + ", formKey=" + formKey + "]";
	}
	
	
	
	
	
	

}
