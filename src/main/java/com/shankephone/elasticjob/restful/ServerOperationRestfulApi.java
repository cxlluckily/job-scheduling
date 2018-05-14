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

package com.shankephone.elasticjob.restful;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.lite.lifecycle.api.ServerStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ServerBriefInfo;
import com.google.common.base.Optional;
import com.shankephone.elasticjob.service.JobAPIService;
import com.shankephone.elasticjob.service.impl.JobAPIServiceImpl;

/**
 * 服务器维度操作的RESTful API.
 *
 * @author caohao
 */
@Component
@Path("/servers")
public final class ServerOperationRestfulApi {
    
    private JobAPIService jobAPIService = new JobAPIServiceImpl();
    
    /**
     * 获取服务器总数.
     * 
     * @return 服务器总数
     */
    @GET
    @Path("/count")
    public int getServersTotalCount() {
    	int count=0;
    	ServerStatisticsAPI api = jobAPIService.getServerStatisticsAPI();
    	if(api!=null){
    		count=api.getServersTotalCount();
    	}
        return count;
    }
    
    /**
     * 获取服务器详情.
     * 
     * @return 服务器详情集合
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllServersBriefInfo() {
    	JSONObject json = new JSONObject();
    	Collection<ServerBriefInfo>	list = null;
    	int	totalCount = 0;
    	JSONArray array = new JSONArray();
		ServerStatisticsAPI api= jobAPIService.getServerStatisticsAPI();
		if(api!=null){
			list = api.getAllServersBriefInfo();
			totalCount = jobAPIService.getServerStatisticsAPI().getServersTotalCount();
			array=JSONArray.parseArray(JSONArray.toJSONString(list));
		}
    	json.put("list", array);
        json.put("totalCount", totalCount);
        return json.toJSONString();
    }
    
    /**
     * 禁用作业.
     *
     * @param serverIp 服务器IP地址
     */
    @POST
    @Path("/{serverIp}/disable")
    public String disableServer(@PathParam("serverIp") final String serverIp) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().disable(Optional.<String>absent(), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 启用作业.
     *
     * @param serverIp 服务器IP地址
     */
    @POST
    @Path("/{serverIp}/enable")
    public String enableServer(@PathParam("serverIp") final String serverIp) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().enable(Optional.<String>absent(), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 终止作业.
     *
     * @param serverIp 服务器IP地址
     */
    @POST
    @Path("/{serverIp}/shutdown")
    public String shutdownServer(@PathParam("serverIp") final String serverIp) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().shutdown(Optional.<String>absent(), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 清理作业.
     *
     * @param serverIp 服务器IP地址
     */
    @POST
    @Path("/{serverIp}/delete")
    public String removeServer(@PathParam("serverIp") final String serverIp) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().remove(Optional.<String>absent(), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 获取该服务器上注册的作业的简明信息.
     *
     * @param serverIp 服务器IP地址
     * @return 作业简明信息对象集合
     */
    @GET
    @Path("/{serverIp}/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJobs(@PathParam("serverIp") final String serverIp) {
    	JSONObject json = new JSONObject();
    	Collection<JobBriefInfo> list=jobAPIService.getJobStatisticsAPI().getJobsBriefInfo(serverIp);
    	JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
    	json.put("list", array);
    	json.put("totalCount", list.size());
        return json.toJSONString();
    }
    
    /**
     * 禁用作业.
     * 
     * @param serverIp 服务器IP地址
     * @param jobName 作业名称
     */
    @POST
    @Path("/{serverIp}/jobs/{jobName}/disable")
    public String disableServerJob(@PathParam("serverIp") final String serverIp, @PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getJobOperatorAPI().disable(Optional.of(jobName), Optional.of(serverIp));
    	json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 启用作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName 作业名称
     */
    @POST
    @Path("/{serverIp}/jobs/{jobName}/enable")
    public String enableServerJob(@PathParam("serverIp") final String serverIp, @PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getJobOperatorAPI().enable(Optional.of(jobName), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 终止作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName 作业名称
     */
    @POST
    @Path("/{serverIp}/jobs/{jobName}/shutdown")
    public String shutdownServerJob(@PathParam("serverIp") final String serverIp, @PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getJobOperatorAPI().shutdown(Optional.of(jobName), Optional.of(serverIp));
    	json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 清理作业.
     *
     * @param serverIp 服务器IP地址
     * @param jobName 作业名称
     */
    @POST
    @Path("/{serverIp}/jobs/{jobName}")
    public String removeServerJob(@PathParam("serverIp") final String serverIp, @PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getJobOperatorAPI().remove(Optional.of(jobName), Optional.of(serverIp));
        json.put("success", true);
        return json.toJSONString();
    }
}
