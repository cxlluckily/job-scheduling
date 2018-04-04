package com.shankephone.job.scheduling.regulation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.job.scheduling.regulation.model.RegulationDetail;

public interface RegulationDetailDao {
	
	public RegulationDetail queryById(@Param("id")String id);

	public Integer insert(RegulationDetail regulationDetail);

	public Integer update(RegulationDetail regulationDetail);

	public Integer deleteById(@Param("id")String id);

	public List<RegulationDetail> queryList();  

}
