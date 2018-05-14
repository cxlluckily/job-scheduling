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
	
	@Override
	public List<DBDataSource> list(Integer start,Integer limit) {

		List<DBDataSource> list = dataSourceDao.queryList(start, limit);

		return list;
	}
	
	@Override
	public List<DBDataSource> cleanList() {

		List<DBDataSource> list = dataSourceDao.queryCleanList();

		return list;
	}
	
	@Override
	public List<DBDataSource> historyList() {

		List<DBDataSource> list = dataSourceDao.queryHistoryList();

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

	@Override
	public long queryTotalCount() {
		return dataSourceDao.queryTotalCount();
	}
}
