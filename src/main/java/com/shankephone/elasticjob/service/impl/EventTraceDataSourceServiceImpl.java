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

import com.google.common.base.Optional;
import com.shankephone.elasticjob.model.EventTraceDataSource;
import com.shankephone.elasticjob.model.EventTraceDataSourceConfigurations;
import com.shankephone.elasticjob.model.GlobalConfiguration;
import com.shankephone.elasticjob.service.EventTraceDataSourceConfigurationService;

/**
 * 事件追踪数据源配置服务实现类.
 *
 * @author caohao
 */
public final class EventTraceDataSourceServiceImpl implements EventTraceDataSourceConfigurationService {
    
    
    @Override
    public EventTraceDataSourceConfigurations loadAll() {
        return loadGlobal().getEventTraceDataSourceConfigurations();
    }
    
    @Override
    public EventTraceDataSource load(final String name) {
        GlobalConfiguration configs = loadGlobal();
        EventTraceDataSource result = find(name, configs.getEventTraceDataSourceConfigurations());
        setActivated(configs, result);
        return result;
    }
    
    @Override
    public EventTraceDataSource find(final String name, final EventTraceDataSourceConfigurations configs) {
        for (EventTraceDataSource each : configs.getEventTraceDataSourceConfiguration()) {
            if (name.equals(each.getName())) {
                return each;
            }
        }
        return null;
    }
    
    private void setActivated(final GlobalConfiguration configs, final EventTraceDataSource toBeConnectedConfig) {
        EventTraceDataSource activatedConfig = findActivatedDataSourceConfiguration(configs);
        if (!toBeConnectedConfig.equals(activatedConfig)) {
            if (null != activatedConfig) {
                activatedConfig.setActivated(false);
            }
            toBeConnectedConfig.setActivated(true);
            //configurationsXmlRepository.save(configs);
        }
    }
    
    @Override
    public Optional<EventTraceDataSource> loadActivated() {
        return Optional.fromNullable(findActivatedDataSourceConfiguration(loadGlobal()));
    }
    
    private EventTraceDataSource findActivatedDataSourceConfiguration(final GlobalConfiguration configs) {
        for (EventTraceDataSource each : configs.getEventTraceDataSourceConfigurations().getEventTraceDataSourceConfiguration()) {
            if (each.isActivated()) {
                return each;
            }
        }
        return null;
    }
    
    @Override
    public boolean add(final EventTraceDataSource config) {
        GlobalConfiguration configs = loadGlobal();
        boolean result = configs.getEventTraceDataSourceConfigurations().getEventTraceDataSourceConfiguration().add(config);
        if (result) {
            //configurationsXmlRepository.save(configs);
        }
        return result;
    }
    
    @Override
    public void delete(final String name) {
        GlobalConfiguration configs = loadGlobal();
        EventTraceDataSource toBeRemovedConfig = find(name, configs.getEventTraceDataSourceConfigurations());
        if (null != toBeRemovedConfig) {
            configs.getEventTraceDataSourceConfigurations().getEventTraceDataSourceConfiguration().remove(toBeRemovedConfig);
            //configurationsXmlRepository.save(configs);
        }
    }
    
    private GlobalConfiguration loadGlobal() {
        GlobalConfiguration result = null;//configurationsXmlRepository.load();
        if (null == result.getEventTraceDataSourceConfigurations()) {
            result.setEventTraceDataSourceConfigurations(new EventTraceDataSourceConfigurations());
        }
        return result;
    }
}
