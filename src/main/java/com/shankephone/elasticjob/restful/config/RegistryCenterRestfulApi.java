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

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
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
@Component
@Path("/registry-center")
public final class RegistryCenterRestfulApi {
    
    public static final String REG_CENTER_CONFIG_KEY = "reg_center_config_key";
    @Resource
    private RegistryCenterService registryCenterService;
    
    @PostConstruct
    public String init() {
    	System.out.println("------------------------------初始化注册中心！"); 
    	Optional<RegistryCenter> opt = registryCenterService.loadActivated();
    	JSONObject json = new JSONObject();
    	if(opt.isPresent()){
    		RegistryCenter rc = opt.get();
    		boolean isConnected = setRegistryCenterNameToSession(rc);
        	registryCenterService.updateActivated(rc.getId());
        	if(isConnected) {
        		json.put("success", true);
        	} else {
        		json.put("success", false);
        		throw new RuntimeException("注册中心初始化失败！！");
        	}
    	}
        return json.toJSONString();
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
    public String load(final @Context HttpServletRequest request) {
    	Optional<RegistryCenter> regCenterConfig = registryCenterService.loadActivated();
        if (regCenterConfig.isPresent()) {
            setRegistryCenterNameToSession(regCenterConfig.get());
        }
        String start = request.getParameter("start");
    	String limit = request.getParameter("limit");
        if(start == null || "".equals(start)){
        	start = "0";
        }
        if(limit == null || "".equals(limit)){
        	limit = "10";
        }
        
        List<RegistryCenter> list = registryCenterService.loadAll(Integer.parseInt(start), Integer.parseInt(limit));
        long count = registryCenterService.queryTotalCount();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        JSONObject json = new JSONObject();
        json.put("list", array);
        json.put("totalCount", count);
        return json.toJSONString();
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
    	boolean isConnected = setRegistryCenterNameToSession(rc);
    	registryCenterService.updateActivated(config.getId());
        JSONObject json = new JSONObject();
        json.put("success", isConnected);
    	return json.toJSONString();
    }
    
    /**
     * 设置全局级别的注册中心
     * @param regCenterConfig
     * @return
     */
    private boolean setRegistryCenterNameToSession(final RegistryCenter regCenterConfig) {
    	RegistryCenter registryCenter = registryCenterService.load(regCenterConfig.getId());
        try {
            RegistryCenterFactory.createCoordinatorRegistryCenter(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
            //生命周期设置
            SessionRegistryCenterConfiguration.setRegistryCenterConfiguration(registryCenter);
        } catch (final RegException ex) {
            return false;
        }
        return true;
    }

    /**
     * 设置会话级别的注册中心
     * @param regCenterConfig
     * @param session
     * @return
     */
	private boolean setRegistryCenterNameToSession(final RegistryCenter regCenterConfig, final HttpSession session) {
    	RegistryCenter registryCenter = registryCenterService.load(regCenterConfig.getId());
    	session.setAttribute(REG_CENTER_CONFIG_KEY, registryCenter);
        try {
            RegistryCenterFactory.createCoordinatorRegistryCenter(regCenterConfig.getZklist(), regCenterConfig.getNamespace(), Optional.fromNullable(regCenterConfig.getDigest()));
            //生命周期设置
            SessionRegistryCenterConfiguration.setRegistryCenterConfiguration((RegistryCenter) session.getAttribute(REG_CENTER_CONFIG_KEY));
        } catch (final RegException ex) {
            return false;
        }
        return true;
    }
}
