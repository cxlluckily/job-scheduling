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

package com.shankephone.elasticjob.service;

import java.util.List;

import com.google.common.base.Optional;
import com.shankephone.elasticjob.model.EventTraceDataSource;
import com.shankephone.elasticjob.model.EventTraceDataSourceConfigurations;

/**
 * 事件追踪数据源配置服务.
 * 
 * @author caohao
 */
public interface EventTraceDataSourceConfigurationService {
    
    /**
     * 读取全部事件追踪数据源配置.
     *
     * @return 全部事件追踪数据源配置
     */
    List<EventTraceDataSource> loadAll();
    
    /**
     * 读取事件追踪数据源配置.
     * 
     * @param id 配置ID
     * @return 事件追踪数据源配置
     */
    EventTraceDataSource load(Long id);
    
    
    /**
     * 读取已连接的事件追踪数据源配置.
     * 
     * @return 已连接的事件追踪数据源配置
     */
    Optional<EventTraceDataSource> loadActivated();
    
    /**
     * 添加事件追踪数据源配置.
     * 
     * @param config 事件追踪数据源配置
     * @return 是否添加成功
     */
    boolean add(EventTraceDataSource config);
    
    /**
     * 删除事件追踪数据源配置.
     *
     * @param id 配置名称
     */
    boolean delete(Long id);

    /**
     * 更新活动的数据源配置
     * @param id
     */
	void updateActivated(Long id);
}
