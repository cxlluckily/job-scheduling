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
import com.shankephone.elasticjob.model.RegistryCenter;

/**
 * 注册中心配置服务.
 *
 * @author zhangliang
 */
public interface RegistryCenterService {
    
    /**
     * 读取全部注册中心配置.
     *
     * @return 全部注册中心配置
     */
    List<RegistryCenter> loadAll();
    
    /**
     * 读取注册中心配置.
     *
     * @param name 配置名称
     * @return 注册中心配置
     */
    RegistryCenter load(Long id);
    
    /**
     * 查找注册中心配置.
     * 
     * @param name 配置名称
     * @param configs 全部注册中心配置
     * @return 注册中心配置
     */
    RegistryCenter find(final String name);
    
    /**
     * 读取已连接的注册中心配置.
     *
     * @return 已连接的注册中心配置
     */
    Optional<RegistryCenter> loadActivated();
    
    /**
     * 添加注册中心配置.
     *
     * @param config 注册中心配置
     * @return 是否添加成功
     */
    boolean add(RegistryCenter config);
    
    /**
     * 删除注册中心配置.
     *
     * @param name 配置名称
     */
    void delete(Long id);

    /**
     * 更新连接状态
     * @param id
     */
	void updateActivated(Long id);
}
