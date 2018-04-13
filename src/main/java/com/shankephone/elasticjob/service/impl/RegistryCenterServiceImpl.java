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
import com.shankephone.elasticjob.dao.RegistryCenterDao;
import com.shankephone.elasticjob.model.RegistryCenter;
import com.shankephone.elasticjob.service.RegistryCenterService;

/**
 * 注册中心配置服务实现类.
 *
 * @author zhangliang
 */
@Service("registryCenterService")
public final class RegistryCenterServiceImpl implements RegistryCenterService {
    
	@Resource
	private RegistryCenterDao registryCenterDao;
	
    @Override
    public List<RegistryCenter> loadAll() {
        return registryCenterDao.queryList();
    }
    
    @Override
    public RegistryCenter load(final Long id) {
        RegistryCenter result = registryCenterDao.queryById(id);
        return result;
    }
    
    public void setActivated(RegistryCenter registryCenter) {
    	registryCenter.setActivated(true);
    	registryCenterDao.insert(registryCenter);
    }
    
    @Override
    public boolean add(final RegistryCenter registryCenter) {
    	int count = registryCenterDao.insert(registryCenter);
        return count > 0 ? true : false;
    }
    
    @Override
    public void delete(final Long id) {
    	registryCenterDao.deleteById(id);
    }

	@Override
	public RegistryCenter find(String name) {
		return registryCenterDao.find(name);
	}

	@Override
	public Optional<RegistryCenter> loadActivated() {
		return Optional.fromNullable(registryCenterDao.loadActivated());
	}

	@Override
	public void updateActivated(Long id) {
		registryCenterDao.updateActivated(id);
		
	}
    
   
}
