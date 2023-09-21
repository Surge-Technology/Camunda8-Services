package com.camunda.activetasklist.model;

public class Total {
	private int value;
	private String relation;
	public int getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "Total [value=" + value + ", relation=" + relation + "]";
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	
	
}
