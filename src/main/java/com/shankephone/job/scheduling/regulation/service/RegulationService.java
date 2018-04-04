package com.shankephone.job.scheduling.regulation.service;

import java.util.List;

import com.shankephone.job.scheduling.regulation.model.Regulation;

public interface RegulationService {
	
	public List<Regulation> list();
	
	public Regulation queryById(String id);
	
	public Integer deleteById(String id);
	
	public Integer insert(Regulation regulation);
	
	public Integer update(Regulation regulation);
	
	

}
