package com.shankephone.job.scheduling.regulation.service;


import java.util.List;

import com.shankephone.job.scheduling.regulation.model.RegulationDetail;

public interface RegulationDetailService {
	
	public List<RegulationDetail> list();
	
	public RegulationDetail queryById(String id);
	
	public Integer deleteById(String id);
	
	public Integer insert(RegulationDetail regulationDetail);
	
	public Integer update(RegulationDetail regulationDetail);
	
	

}
