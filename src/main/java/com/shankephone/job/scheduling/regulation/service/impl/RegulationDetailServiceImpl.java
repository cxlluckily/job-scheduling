package com.shankephone.job.scheduling.regulation.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shankephone.job.scheduling.regulation.dao.RegulationDetailDao;
import com.shankephone.job.scheduling.regulation.model.RegulationDetail;
import com.shankephone.job.scheduling.regulation.service.RegulationDetailService;

@Service
public class RegulationDetailServiceImpl implements RegulationDetailService{
	
	@Resource
	private RegulationDetailDao regulationDetailDao;

	@Override
	public RegulationDetail queryById(String id) {
		return regulationDetailDao.queryById(id);
	}

	@Override
	public Integer insert(RegulationDetail regulationDetail) {
		return regulationDetailDao.insert(regulationDetail);
	}

	@Override
	public Integer update(RegulationDetail regulationDetail) {
		return regulationDetailDao.update(regulationDetail);
	}

	@Override
	public Integer deleteById(String id) {
		return regulationDetailDao.deleteById(id);
	}

	@Override
	public long queryCountById(String id) {
		return regulationDetailDao.queryCountById(id);
	}

	@Override
	public long queryMaxId() {
		return regulationDetailDao.queryMaxId();
	}

}
