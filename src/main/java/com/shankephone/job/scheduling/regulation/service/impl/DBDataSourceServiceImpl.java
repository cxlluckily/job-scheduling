package com.shankephone.job.scheduling.regulation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shankephone.job.scheduling.regulation.dao.DBDataSourceDao;
import com.shankephone.job.scheduling.regulation.model.DBDataSource;
import com.shankephone.job.scheduling.regulation.service.DBDataSourceService;

@Service
public class DBDataSourceServiceImpl implements DBDataSourceService {

	@Resource
	private DBDataSourceDao dataSourceDao;

	public List<DBDataSource> list() {

		List<DBDataSource> list = dataSourceDao.queryList();

		return list;
	}

	@Override
	public DBDataSource queryById(String id) {
		return dataSourceDao.queryById(id);
	}

	@Override
	public Integer deleteById(String id) {
		return dataSourceDao.deleteById(id);
	}

	@Override
	public Integer insert(DBDataSource source) {
		return dataSourceDao.insert(source);
	}

	@Override
	public Integer update(DBDataSource source) {
		return dataSourceDao.update(source);
	}

}
