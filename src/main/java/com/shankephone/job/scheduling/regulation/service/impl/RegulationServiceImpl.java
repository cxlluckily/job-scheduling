package com.shankephone.job.scheduling.regulation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shankephone.job.scheduling.regulation.dao.RegulationDao;
import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.service.RegulationService;

import com.shankephone.job.scheduling.regulation.model.JobRegRelation;

@Service
public class RegulationServiceImpl implements RegulationService {

	@Resource
	private RegulationDao regulationDao;
	
	@Override
	public List<Regulation> list(Integer start,Integer limit) {

		List<Regulation> list = regulationDao.queryList(start, limit);

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
	public Integer insertJob(JobRegRelation jobRegRelation) {
		return regulationDao.insertJob(jobRegRelation);
	}


	@Override
	public Integer update(Regulation regulation) {
		return regulationDao.update(regulation);
	}
	
	@Override
	public long queryTotalCount() {
		return regulationDao.queryTotalCount();
	}
	
	@Override
	public long queryMaxId() {
		return regulationDao.queryMaxId();
	}

	@Override
	public long queryCountByDataSource(long dataSourceId) {
		return regulationDao.queryCountByDataSource(dataSourceId);
	}

	@Override
	public long queryCountByJob(long regId) {
		return regulationDao.queryCountByJob(regId);
	}
}
