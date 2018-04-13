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

import com.dangdang.ddframe.job.lite.lifecycle.api.JobAPIFactory;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ServerStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ShardingOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.ShardingStatisticsAPI;
import com.google.common.base.Optional;
import com.shankephone.elasticjob.model.RegistryCenter;
import com.shankephone.elasticjob.service.JobAPIService;
import com.shankephone.elasticjob.util.SessionRegistryCenterConfiguration;

/**
 * 作业API服务实现类.
 * 
 * @author zhangliang 
 */
public final class JobAPIServiceImpl implements JobAPIService {
    
    @Override
    public JobSettingsAPI getJobSettingsAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createJobSettingsAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
    @Override
    public JobOperateAPI getJobOperatorAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createJobOperateAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
    @Override
    public ShardingOperateAPI getShardingOperateAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createShardingOperateAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
    @Override
    public JobStatisticsAPI getJobStatisticsAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createJobStatisticsAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
    @Override
    public ServerStatisticsAPI getServerStatisticsAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createServerStatisticsAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
    @Override
    public ShardingStatisticsAPI getShardingStatisticsAPI() {
        RegistryCenter regCenterConfig = SessionRegistryCenterConfiguration.getRegistryCenterConfiguration();
        return JobAPIFactory.createShardingStatisticsAPI(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
    }
    
}
