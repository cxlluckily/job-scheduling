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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo.JobStatus;
import com.dangdang.ddframe.job.lite.lifecycle.domain.ShardingInfo;
import com.google.common.base.Optional;
import com.jcraft.jsch.Session;
import com.shankephone.elasticjob.service.JobAPIService;
import com.shankephone.elasticjob.service.JobNodeRelationService;
import com.shankephone.elasticjob.service.impl.JobAPIServiceImpl;
import com.shankephone.elasticjob.util.SftpTransferUtil;
import com.shankephone.job.scheduling.job.model.JobSetting;
import com.shankephone.job.scheduling.job.service.JobSettingService;

/**
 * 作业维度操作的RESTful API.
 *
 * @author caohao
 */
@Component
@Path("/jobs")
@Slf4j
public final class JobOperationRestfulApi {
    
    private JobAPIService jobAPIService = new JobAPIServiceImpl();
    @Resource
    private JobSettingService jobSettingService;
    @Resource
    private JobNodeRelationService jobNodeRelationService;
    
    /**
     * 获取作业总数.
     * 
     * @return 作业总数
     */
    @POST
    @Path("/count")
    public int getJobsTotalCount() {
        return jobAPIService.getJobStatisticsAPI().getJobsTotalCount();
    }
    
    /**
     * 获取作业详情.
     * 
     * @return 作业详情集合
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllJobsBriefInfo(final @Context HttpServletRequest request) {
    	String start = request.getParameter("start");
    	String limit = request.getParameter("limit");
        if(start == null || "".equals(start)){
        	start = "0";
        }
        if(limit == null || "".equals(limit)){
        	limit = "10";
        }
    	Collection<JobBriefInfo> ls = jobAPIService.getJobStatisticsAPI().getAllJobsBriefInfo();
        Map<String, JobBriefInfo> map = new HashMap<String, JobBriefInfo>();
    	for(JobBriefInfo info : ls){
        	map.put(info.getJobName(), info);
        }
    	List<JobSetting> list = jobSettingService.list(Integer.parseInt(start), Integer.parseInt(limit));
    	for(JobSetting setting : list){
    		JobBriefInfo info = map.get(setting.getJobName());
    		if(info != null){
    			JobStatus status = info.getStatus();
        		int shardCount = info.getShardingTotalCount();
        		if(status != null){
        			String name = status.name();
        			log.info("==============================获取作业状态：" + name);
        			if(name.equals("OK")){
        				setting.setStatus("2");
        			}
        			if(name.equals("CRASHED")){
        				setting.setStatus("3");
        			}
        			if(name.equals("DISABLED")){
        				setting.setStatus("4");
        			}
        			if(name.equals("SHARDING_FLAG")){
        				setting.setStatus("5");
        			}
        		}
        		setting.setShardingTotalCount(shardCount);
    		}
    	}
    	long count = jobSettingService.queryTotalCount();
    	JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        JSONObject json = new JSONObject();
        json.put("list", array);
        json.put("totalCount", count);
        return json.toJSONString();
    }
    
    /**
     * 触发作业.
     * 
     * @param jobName 作业名称
     */
    @POST
    @Path("/{jobName}/trigger")
    public String triggerJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().trigger(Optional.of(jobName), Optional.<String>absent());
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 禁用作业.
     * 
     * @param jobName 作业名称
     */
    @POST
    @Path("/{jobName}/disable")
    public String disableJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().disable(Optional.of(jobName), Optional.<String>absent());
        jobSettingService.disbale(jobName);
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 启用作业.
     *
     * @param jobName 作业名称
     */
    @POST
    @Path("/{jobName}/enable")
    public String enableJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
        jobAPIService.getJobOperatorAPI().enable(Optional.of(jobName), Optional.<String>absent());
        jobSettingService.enable(jobName);
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 终止作业.
     * 
     * @param jobName 作业名称
     */
    @POST
    @Path("/{jobName}/shutdown")
    public String shutdownJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	JobSetting job = jobSettingService.findByName(jobName);
        jobAPIService.getJobOperatorAPI().shutdown(Optional.of(jobName), Optional.<String>absent());
        jobSettingService.shutdown(jobName);
        boolean result = killJob(jobName, job.getId());
        json.put("success", result);
        return json.toJSONString();
    }
    
    private boolean killJob(String jobName, Long id){
    	boolean result = false;
    	//获取作业运行的服务节点
    	List<Map<String,Object>> list = jobNodeRelationService.queryListByJobId(id);
    	for(Map<String,Object> map : list){
    		String ip = String.valueOf(map.get("ip_address"));
    		int port = (Integer)map.get("port");
    		String username = String.valueOf(map.get("username"));
    		String password = String.valueOf(map.get("password"));
    		String workDir = String.valueOf(map.get("work_dir"));
    		try {
    			String splitor = "\"|\"";
				Session session = SftpTransferUtil.openSession(ip, port, username, password);
				String command = "ps aux| grep " + jobName + "| awk '{print $1" + splitor + "$2" + splitor + "$11}'";
				log.info(ip + ":" + port + "(" + username + ")   " + workDir);
				log.info(command);
				List<String> processes = SftpTransferUtil.runCmd(session, command, "utf-8", false);
				String killCommand = "kill -9 ";
				for(String process : processes){
					String [] proInfos = process.split("\\|");
					String user = proInfos[0];
					String pid = proInfos[1];
					String cmd = proInfos[2];
					if(username.equals(user) && cmd.equals("java")){
						String execommand = killCommand + pid;
						SftpTransferUtil.runCmd(session, execommand, "utf-8",false);
						log.info("执行命令成功：" + execommand) ;
					}
				}
				session.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("执行命令异常！", e);
			}
    	}
    	result = true;
    	return result;
    }
    
    /**
     * 获取分片信息.
     * 
     * @param jobName 作业名称
     * @return 分片信息集合
     */
    @GET
    @Path("/{jobName}/sharding")
    public String getShardingInfo(@PathParam("jobName") final String jobName) {
    	Collection<ShardingInfo> list = jobAPIService.getShardingStatisticsAPI().getShardingInfo(jobName);
    	JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        JSONObject json = new JSONObject();
        json.put("list", array);
        json.put("totalCount", list.size());
        return json.toJSONString();
    }
    
    @POST
    @Path("/{jobName}/sharding/{item}/disable")
    public String disableSharding(@PathParam("jobName") final String jobName, @PathParam("item") final String item) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getShardingOperateAPI().disable(jobName, item);
    	json.put("success", true);
        return json.toJSONString();
    }
    
    @POST
    @Path("/{jobName}/sharding/{item}/enable")
    public String enableSharding(@PathParam("jobName") final String jobName, @PathParam("item") final String item) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobAPIService.getShardingOperateAPI().enable(jobName, item);
        json.put("success", true);
        return json.toJSONString();
    }
}
