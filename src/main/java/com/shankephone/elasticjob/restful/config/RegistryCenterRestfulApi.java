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

package com.shankephone.elasticjob.restful.config;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.lite.lifecycle.internal.reg.RegistryCenterFactory;
import com.dangdang.ddframe.job.reg.exception.RegException;
import com.google.common.base.Optional;
import com.shankephone.elasticjob.model.RegistryCenter;
import com.shankephone.elasticjob.service.RegistryCenterService;
import com.shankephone.elasticjob.util.SessionRegistryCenterConfiguration;

/**
 * 注册中心配置的RESTful API.
 *
 * @author caohao
 */
@Path("/registry-center")
public final class RegistryCenterRestfulApi {
    
    public static final String REG_CENTER_CONFIG_KEY = "reg_center_config_key";
    
    private RegistryCenterService registryCenterService;
    
    public RegistryCenterRestfulApi(){
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	registryCenterService = (RegistryCenterService)applicationContext.getBean("registryCenterService");
    }
    
    
    /**
     * 判断是否存在已连接的注册中心配置.
     *
     * @param request HTTP请求
     * @return 是否存在已连接的注册中心配置
     */
    @GET
    @Path("/activated")
    public String activated(final @Context HttpServletRequest request) {
        boolean result = registryCenterService.loadActivated().isPresent();
        JSONObject json = new JSONObject();
        json.put("success", result);
        return json.toJSONString();
    }
    
    /**
     * 读取注册中心配置集合.
     * 
     * @param request HTTP请求
     * @return 注册中心配置集合
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RegistryCenter> load(final @Context HttpServletRequest request) {
    	Optional<RegistryCenter> regCenterConfig = registryCenterService.loadActivated();
        if (regCenterConfig.isPresent()) {
            setRegistryCenterNameToSession(regCenterConfig.get(), request.getSession());
        }
        List<RegistryCenter> list = registryCenterService.loadAll();
        return list;
    }
    
    /**
     * 添加注册中心.
     * 
     * @param config 注册中心配置
     * @return 是否添加成功
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String add(final RegistryCenter config) {
    	config.setCreateTime(new Date());
    	boolean result = registryCenterService.add(config);
    	JSONObject json = new JSONObject();
		json.put("success", result);
        return json.toJSONString();
    }
    
    /**
     * 删除注册中心.
     *
     * @param config 注册中心配置
     */
    @POST
    @Path("/delete")
    public String delete(final RegistryCenter config) {
    	JSONObject json = new JSONObject();
        try {
			registryCenterService.delete(config.getId());
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
		}
        return json.toJSONString();
    }
    
    @POST
    @Path("/connect")
    public String connect(final RegistryCenter config, final @Context HttpServletRequest request) {
    	RegistryCenter rc = registryCenterService.load(config.getId());
    	boolean isConnected = setRegistryCenterNameToSession(rc, request.getSession());
    	registryCenterService.updateActivated(config.getId());
        JSONObject json = new JSONObject();
        json.put("success", isConnected);
    	return json.toJSONString();
    }
    
    private boolean setRegistryCenterNameToSession(final RegistryCenter regCenterConfig, final HttpSession session) {
    	RegistryCenter registryCenter = registryCenterService.load(regCenterConfig.getId());
    	session.setAttribute(REG_CENTER_CONFIG_KEY, registryCenter);
        try {
            RegistryCenterFactory.createCoordinatorRegistryCenter(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
            SessionRegistryCenterConfiguration.setRegistryCenterConfiguration((RegistryCenter) session.getAttribute(REG_CENTER_CONFIG_KEY));
        } catch (final RegException ex) {
            return false;
        }
        return true;
    }
}
