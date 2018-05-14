package com.shankephone.job.scheduling.regulation.model;

import com.shankephone.job.scheduling.common.BaseModel;

public class RegulationDetail extends BaseModel{
	private static final long serialVersionUID = 1L;
	private String id;
	private String reg_id;
	private String col_name;
	private String col_value;
	private String operator;
	private String type;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
