package com.shankephone.job.scheduling.regulation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.job.scheduling.regulation.model.DBDataSource;

public interface DBDataSourceDao {
	
	public DBDataSource queryById(@Param("id")String id);

	public Integer insert(DBDataSource source);

	public Integer update(DBDataSource source);

	public Integer deleteById(@Param("id")String id);

	public List<DBDataSource> queryList();  

}
