package com.shankephone.job.scheduling.regulation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.model.JobRegRelation;

public interface RegulationDao {
	
	public Regulation queryById(@Param("id")String id);

	public Integer insert(Regulation regulation);
	
	public Integer insertJob(JobRegRelation jobRegRelation);

	public Integer update(Regulation regulation);

	public Integer deleteById(@Param("id")String id);

	public List<Regulation> queryList(@Param("start")Integer start, @Param("limit")Integer limit);  
	
	public long queryTotalCount(); 
	
	public long queryCountByDataSource(@Param("dataSourceId")long dataSourceId); 
	
	public long queryCountByJob(@Param("regId")long regId); 
	
	public long queryMaxId();

}