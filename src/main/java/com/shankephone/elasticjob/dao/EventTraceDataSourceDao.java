package com.shankephone.elasticjob.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.elasticjob.model.EventTraceDataSource;

public interface EventTraceDataSourceDao {
	
	public EventTraceDataSource queryById(@Param("id")Long id);

	public Integer insert(EventTraceDataSource source);

	public Integer update(EventTraceDataSource source);

	public Integer deleteById(@Param("id")Long id);

	public List<EventTraceDataSource> queryList();

	public EventTraceDataSource loadActivated();

	public void updateActivated(@Param("id")Long id);  

}
