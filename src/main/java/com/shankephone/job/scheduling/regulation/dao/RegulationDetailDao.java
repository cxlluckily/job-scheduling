package com.shankephone.job.scheduling.regulation.dao;

import org.apache.ibatis.annotations.Param;

import com.shankephone.job.scheduling.regulation.model.RegulationDetail;

public interface RegulationDetailDao {
	
	public RegulationDetail queryById(@Param("id")String id);

	public Integer insert(RegulationDetail regulationDetail);
	
	public Integer update(RegulationDetail regulationDetail);

	public Integer deleteById(@Param("id")String id);

	public long queryCountById(@Param("id")String id); 
	
	public long queryMaxId();

}