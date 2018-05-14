package com.shankephone.job.scheduling.regulation.service;

import com.shankephone.job.scheduling.regulation.model.RegulationDetail;

public interface RegulationDetailService {

	public RegulationDetail queryById(String id);

	public Integer insert(RegulationDetail regulationDetail);
	
	public Integer update(RegulationDetail regulationDetail);

	public Integer deleteById(String id);

	public long queryCountById(String id); 
	
	public long queryMaxId();
}
