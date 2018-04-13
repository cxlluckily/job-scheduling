package com.shankephone.job.scheduling.regulation.model;

import java.util.Date;

public class Regulation extends BaseModel{
	private static final long serialVersionUID = 1L;
	private String id;
	private String source_id;
	private String name;
	private String db_name;
	private String table_name;
	private String col_name;
	private String col_value;
	private String operator;
	private String sql_txt;
	private Date create_time;
	private Date modify_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSource_id() {
		return source_id;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql_txt() {
		return sql_txt;
	}

	public void setSql_txt(String sql_txt) {
		this.sql_txt = sql_txt;
	}

	
}
