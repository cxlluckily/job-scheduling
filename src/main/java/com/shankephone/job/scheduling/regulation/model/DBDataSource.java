package com.shankephone.job.scheduling.regulation.model;

import java.util.Date;

import com.shankephone.job.scheduling.common.BaseModel;

/**
 * 数据源
 * 
 * @author fengql
 * @version 2018年3月30日 下午6:17:41
 */
public class DBDataSource extends BaseModel{
	private static final long serialVersionUID = 1L;
	private String id;
	private String master_url;
	private String master_username;
	private String master_password;
	private String slave_url;
	private String slave_username;
	private String slave_password;
	private Date create_time;
	private Date modify_time;
	private String source_type;
	private String source_name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaster_url() {
		return master_url;
	}

	public void setMaster_url(String master_url) {
		this.master_url = master_url;
	}

	public String getMaster_username() {
		return master_username;
	}

	public void setMaster_username(String master_username) {
		this.master_username = master_username;
	}

	public String getMaster_password() {
		return master_password;
	}

	public void setMaster_password(String master_password) {
		this.master_password = master_password;
	}

	public String getSlave_url() {
		return slave_url;
	}

	public void setSlave_url(String slave_url) {
		this.slave_url = slave_url;
	}

	public String getSlave_username() {
		return slave_username;
	}

	public void setSlave_username(String slave_username) {
		this.slave_username = slave_username;
	}

	public String getSlave_password() {
		return slave_password;
	}

	public void setSlave_password(String slave_password) {
		this.slave_password = slave_password;
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

	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
    
	

}
