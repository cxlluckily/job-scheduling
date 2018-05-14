package com.shankephone.job.scheduling.regulation.service;

import java.util.List;

import com.shankephone.job.scheduling.regulation.model.DBDataSource;

public interface DBDataSourceService {
	
	public List<DBDataSource> list(Integer start, Integer limit);
	
	public List<DBDataSource> cleanList();
	
	public List<DBDataSource> historyList();
	
	public DBDataSource queryById(String id);
	
	public Integer deleteById(String id);
	
	public Integer insert(DBDataSource source);
	
	public Integer update(DBDataSource source);
	
	long queryTotalCount();

}
