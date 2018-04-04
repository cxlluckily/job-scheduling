package com.shankephone.job.scheduling.regulation.model;

public class RegulationDetail {
	private String id;
	private String regulation_id;
	private String col_name;
	private String col_value;
	private String operator;
	private String col_type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegulation_id() {
		return regulation_id;
	}
	public void setRegulation_id(String regulation_id) {
		this.regulation_id = regulation_id;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public String getCol_value() {
		return col_value;
	}
	public void setCol_value(String col_value) {
		this.col_value = col_value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getCol_type() {
		return col_type;
	}
	public void setCol_type(String col_type) {
		this.col_type = col_type;
	}
	
	
}
