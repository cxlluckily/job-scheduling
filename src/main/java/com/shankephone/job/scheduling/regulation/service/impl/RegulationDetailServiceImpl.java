package com.shankephone.job.scheduling.regulation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shankephone.job.scheduling.regulation.dao.RegulationDetailDao;
import com.shankephone.job.scheduling.regulation.model.RegulationDetail;
import com.shankephone.job.scheduling.regulation.service.RegulationDetailService;

@Service
public class RegulationDetailServiceImpl implements RegulationDetailService {

	@Resource
	private RegulationDetailDao regulationDetailDao;

	public List<RegulationDetail> list() {

		List<RegulationDetail> list = regulationDetailDao.queryList();

		return list;
	}

	@Override
	public RegulationDetail queryById(String id) {
		return regulationDetailDao.queryById(id);
	}

	@Override
	public Integer deleteById(String id) {
		return regulationDetailDao.deleteById(id);
	}

	@Override
	public Integer insert(RegulationDetail regulationDetail) {
		return regulationDetailDao.insert(regulationDetail);
	}

	@Override
	public Integer update(RegulationDetail regulationDetail) {
		return regulationDetailDao.update(regulationDetail);
	}

}
