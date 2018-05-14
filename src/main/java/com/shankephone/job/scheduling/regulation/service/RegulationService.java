package com.shankephone.job.scheduling.regulation.service;

import java.util.List;

import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.model.JobRegRelation;

public interface RegulationService {
	
	public List<Regulation> list(Integer start, Integer limit);
	
	public Regulation queryById(String id);
	
	public Integer deleteById(String id);
	
	public Integer insert(Regulation regulation);
	
	public Integer insertJob(JobRegRelation jobRegRelation);
	
	public Integer update(Regulation regulation);
	
	long queryTotalCount();
	
	long queryCountByDataSource(long dataSourceId);
	
	long queryCountByJob(long regId); 
	
	long queryMaxId();

}
