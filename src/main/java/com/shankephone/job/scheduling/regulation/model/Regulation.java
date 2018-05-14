package com.shankephone.job.scheduling.regulation.model;

import java.util.Date;

import com.shankephone.job.scheduling.common.BaseModel;

public class Regulation extends BaseModel{
	private static final long serialVersionUID = 1L;
	private String id;
	private String source_id;
	private String history_source_id;
	private String name;
	private String db_name;
	private String history_db_name;
	private String history_table_name;
	private String table_name;
	private String sql_txt;
	private String check_sql;
	private String check_history_sql;
	private Date create_time;
	private Date modify_time;
	private String id_job_setting;
	private String job_name;

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

	public String getHistory_source_id() {
		return history_source_id;
	}

	public void setHistory_source_id(String history_source_id) {
		this.history_source_id = history_source_id;
	}

	public String getHistory_db_name() {
		return history_db_name;
	}

	public void setHistory_db_name(String history_db_name) {
		this.history_db_name = history_db_name;
	}

	public String getHistory_table_name() {
		return history_table_name;
	}

	public void setHistory_table_name(String history_table_name) {
		this.history_table_name = history_table_name;
	}

	public String getCheck_sql() {
		return check_sql;
	}

	public void setCheck_sql(String check_sql) {
		this.check_sql = check_sql;
	}

	public String getCheck_history_sql() {
		return check_history_sql;
	}

	public void setCheck_history_sql(String check_history_sql) {
		this.check_history_sql = check_history_sql;
	}

	public String getId_job_setting() {
		return id_job_setting;
	}

	public void setId_job_setting(String id_job_setting) {
		this.id_job_setting = id_job_setting;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}


	
	
	
}
