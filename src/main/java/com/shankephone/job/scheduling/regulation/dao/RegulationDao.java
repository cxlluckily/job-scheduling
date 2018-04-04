package com.shankephone.job.scheduling.regulation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.job.scheduling.regulation.model.Regulation;

public interface RegulationDao {
	
	public Regulation queryById(@Param("id")String id);

	public Integer insert(Regulation regulation);

	public Integer update(Regulation regulation);

	public Integer deleteById(@Param("id")String id);

	public List<Regulation> queryList();  

}