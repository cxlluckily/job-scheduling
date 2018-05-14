/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.shankephone.elasticjob.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.shankephone.elasticjob.dao.EventTraceDataSourceDao;
import com.shankephone.elasticjob.model.EventTraceDataSource;
import com.shankephone.elasticjob.model.EventTraceDataSourceConfigurations;
import com.shankephone.elasticjob.service.EventTraceDataSourceConfigurationService;

/**
 * 事件追踪数据源配置服务实现类.
 *
 * @author caohao
 */
@Service("eventTraceDataSourceConfigurationService")
public final class EventTraceDataSourceServiceImpl implements EventTraceDataSourceConfigurationService {
    
	@Resource
	private EventTraceDataSourceDao eventTraceDataSourceDao;
    
    @Override
    public List<EventTraceDataSource> loadAll() {
        return eventTraceDataSourceDao.queryList();
    }
    
    @Override
    public EventTraceDataSource load(final Long id) {
        return eventTraceDataSourceDao.queryById(id);
    }
    
    @Override
    public Optional<EventTraceDataSource> loadActivated() {
        return Optional.fromNullable(eventTraceDataSourceDao.loadActivated());
    }
    
    @Override
    public boolean add(final EventTraceDataSource config) {
    	int count = eventTraceDataSourceDao.insert(config);
        return count > 0 ? true : false;
    }
    
    @Override
    public boolean delete(final Long id) {
    	int count = eventTraceDataSourceDao.deleteById(id);
    	return count > 0 ? true : false;
    }

	@Override
	public void updateActivated(Long id) {
		
		eventTraceDataSourceDao.updateActivated(id);
	}
    
   
}
