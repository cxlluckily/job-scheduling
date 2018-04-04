package com.shankephone.job.scheduling.regulation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shankephone.job.scheduling.regulation.dao.RegulationDao;
import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.service.RegulationService;

@Service
public class RegulationServiceImpl implements RegulationService {

	@Resource
	private RegulationDao regulationDao;

	public List<Regulation> list() {

		List<Regulation> list = regulationDao.queryList();

		return list;
	}

	@Override
	public Regulation queryById(String id) {
		return regulationDao.queryById(id);
	}

	@Override
	public Integer deleteById(String id) {
		return regulationDao.deleteById(id);
	}

	@Override
	public Integer insert(Regulation regulation) {
		return regulationDao.insert(regulation);
	}

	@Override
	public Integer update(Regulation regulation) {
		return regulationDao.update(regulation);
	}

}
