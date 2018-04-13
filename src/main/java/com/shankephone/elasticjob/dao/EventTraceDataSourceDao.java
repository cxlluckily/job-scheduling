package com.shankephone.elasticjob.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.elasticjob.model.EventTraceDataSource;

public interface EventTraceDataSourceDao {
	
	public EventTraceDataSource queryById(@Param("id")String id);

	public Integer insert(EventTraceDataSource source);

	public Integer update(EventTraceDataSource source);

	public Integer deleteById(@Param("id")String id);

	public List<EventTraceDataSource> queryList();  

}
