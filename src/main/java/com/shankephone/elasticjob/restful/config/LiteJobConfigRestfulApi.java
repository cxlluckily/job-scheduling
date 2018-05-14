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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobSettings;
import com.jcraft.jsch.Session;
import com.shankephone.elasticjob.model.JobNodeRelation;
import com.shankephone.elasticjob.service.JobAPIService;
import com.shankephone.elasticjob.service.JobNodeRelationService;
import com.shankephone.elasticjob.service.impl.JobAPIServiceImpl;
import com.shankephone.elasticjob.util.SftpTransferUtil;
import com.shankephone.job.scheduling.job.model.JobSetting;
import com.shankephone.job.scheduling.job.service.JobSettingService;
import com.shankephone.job.scheduling.regulation.model.ServerNode;
import com.shankephone.job.scheduling.regulation.service.ServerNodeService;
import com.shankephone.job.scheduling.upload.model.UploadFile;
import com.shankephone.job.scheduling.upload.service.UploadFileService;

/**
 * 作业配置的RESTful API.
 *
 * @author caohao
 */
@Slf4j
@Component
@Path("/jobs/config")
public final class LiteJobConfigRestfulApi {
    
    private JobAPIService jobAPIService = new JobAPIServiceImpl();
    private static final int RANDOM_NUM = 2;
    
    //作业状态：0-已创建,1-已发布，2-已启动，3-已故障,4-已禁用, 5-已分片，9-已停止
    private static final String JOB_STATUS_CREATED = "0";
    private static final String JOB_STATUS_PUBLISHED = "1";
    private static final String JOB_STATUS_OK = "2";
    private static final String JOB_STATUS_CRASHED = "3";
    private static final String JOB_STATUS_DISABLED = "4";
    private static final String JOB_STATUS_SHARDING = "5";
    private static final String JOB_STATUS_SHUTDOWN = "9";
    
    @Resource
    private JobSettingService jobSettingService;
    @Resource
    private ServerNodeService serverNodeService;
    @Resource
    private UploadFileService uploadFileService;
    @Resource
    private JobNodeRelationService jobNodeRelationService;
    
    /**
     * 获取作业配置.
     * 
     * @param jobName 作业名称
     * @return 作业配置
     */
    @POST
    @Path("/{jobName}")
    @Produces(MediaType.APPLICATION_JSON)
    public JobSetting getJobSettings(@PathParam("jobName") final String jobName) {
    	JobSetting settings = jobSettingService.findByName(jobName);
    	//JobSettings settings = jobAPIService.getJobSettingsAPI().getJobSettings(jobName);
    	
    	return settings;
    }
    
    /**
     * 获取作业配置.
     * 
     * @param jobName 作业名称
     * @return 作业配置
     */
    @POST
    @Path("/{jobName}/exist")
    @Produces(MediaType.APPLICATION_JSON)
    public String existJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	JobSetting settings = jobSettingService.findByName(jobName);
    	//JobSettings settings = jobAPIService.getJobSettingsAPI().getJobSettings(jobName);
    	if(settings == null){
    		json.put("success", true);
    	} else {
    		json.put("success", false);
    	}
    	return json.toJSONString();
    }
    
    /**
     * 修改作业配置.
     * 
     * @param jobSettings 作业配置
     */
    @POST
    @Path("/update")
    public String updateJobSettings(final JobSetting jobSetting) {
    	JSONObject json = new JSONObject();
        try {
        	JobSettings settings = new JobSettings();
        	BeanUtils.copyProperties(jobSetting, settings);
        	jobSettingService.update(jobSetting);
			jobAPIService.getJobSettingsAPI().updateJobSettings(settings);
			json.put("success", true);
		} catch (Exception e) {
			json.put("success", false);
			e.printStackTrace();
		}
        return json.toJSONString();
    }
    
    /**
     * 删除作业配置.
     * 
     * @param jobName 作业名称
     */
    @POST
    @Path("/delete/{jobName}")
    public String removeJob(@PathParam("jobName") final String jobName) {
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	jobSettingService.deleteByName(jobName);
        jobAPIService.getJobSettingsAPI().removeJobSettings(jobName);
        json.put("success", true);
        return json.toJSONString();
    }
    
    /**
     * 发布作业
     * @param jobName
     * @param jobId
     * @return
     */
    @POST
    @Path("/deploy")
    public String deploy(final JobSetting jobSetting){
    	Date start = new Date();
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	log.info("===============作业【" +jobSetting.getJobName()+ "】开始发布....================");
    	//获取作业对应的jar包
    	Long id = jobSetting.getJarId();
    	UploadFile jarInfo = uploadFileService.findById(id);
    	String filePath = jarInfo.getFilePath();    	//获取配置的服务器节点和节点的根目录
    	List<ServerNode> list = serverNodeService.queryForList();
    	//获取该作业原有的作业与节点关系
    	clearOldRelations(jobSetting.getId());
    	
    	//随机选取发布的节点
    	List<ServerNode> rndList = generateRandomList(list);
    	for(ServerNode node : rndList){
			String root = node.getRootDir();
			if(!root.endsWith("/")){
				root +=  "/";
			}
    		//写入作业与服务器的关联关系
    		JobNodeRelation jnr = new JobNodeRelation();
    		jnr.setJobId(jobSetting.getId());
    		jnr.setNodeId(node.getId());
    		jobNodeRelationService.insert(jnr);
    		jnr.setId(jnr.getId()); 
    		jnr.setWorkDir(root + jnr.getId());
    		jobNodeRelationService.update(jnr);
    		//分发jar包到服务器节点的相对目录
    		String ip = node.getIpAddress();
    		int port = node.getPort();
    		String username = node.getUsername();
    		String password = node.getPassword();
    		String targetPath = jnr.getWorkDir();
    		distribute(ip, port, username, password, filePath, targetPath, jarInfo.getFileRealName());
    	}
    	//设置状态为已发布
    	jobSetting.setStatus(JOB_STATUS_PUBLISHED);
    	jobSettingService.updateStatus(jobSetting);
    	JobSettings settings = new JobSettings();
    	BeanUtils.copyProperties(jobSetting, settings);
    	jobAPIService.getJobSettingsAPI().updateJobSettings(settings);
    	json.put("success", true);
    	Date end = new Date();
    	log.info("===============作业【" +jobSetting.getJobName()+ "】发布成功! 耗时：" + (end.getTime() - start.getTime()) + "ms ================");
    	return json.toJSONString();
    }

	private void clearOldRelations(final Long jobId) {
		List<Map<String,Object>> oldRelations = jobNodeRelationService.queryListByJobId(jobId);
    	if(oldRelations != null && oldRelations.size() > 0){
    		for(Map<String,Object> rmap : oldRelations){
    			String ip = (String)rmap.get("ip_address");
    			Integer port = (Integer)rmap.get("port");
    			String username = (String)rmap.get("username");
    			String password = (String)rmap.get("password");
    			String work_dir = (String)rmap.get("work_dir");
    			SftpTransferUtil.deleteDirectory(ip, port, username, password, work_dir);
    		}
    		//清除旧的作业与服务节点关联
        	jobNodeRelationService.deleteByJobId(jobId);
    	}
	}
    
	/**
     * 生成随机的服务节点
     * @param list
     * @return
     */
    private List<ServerNode> generateRandomList(List<ServerNode> list) {
    	List<ServerNode> rndList = new ArrayList<ServerNode>();
    	for(int i = 0; i < RANDOM_NUM; i++){
    		int num=(int)(Math.random() * list.size()); 
    		rndList.add(list.get(num));
    	}
		return rndList;
	}

	/**
     * 启动作业
     * @param jobName
     * @param jobId
     * @return
     */
    @POST
    @Path("/start")
    public String start(final JobSetting jobSetting){
    	Date start = new Date();
    	JSONObject json = new JSONObject();
    	json.put("success", false);
    	long programId = jobSetting.getJarId();
    	UploadFile pkg = uploadFileService.findById(programId);
    	log.info("==================" +jobSetting.getJobName() + "开始启动作业=================");
    	//获取作业运行的服务节点
    	List<Map<String,Object>> list = jobNodeRelationService.queryListByJobId(jobSetting.getId());
    	Collections.shuffle(list);
    	for(Map<String,Object> map : list){
    		String ip = String.valueOf(map.get("ip_address"));
    		int port = (Integer)map.get("port");
    		String username = String.valueOf(map.get("username"));
    		String password = String.valueOf(map.get("password"));
    		String workDir = String.valueOf(map.get("work_dir"));
    		try {
				Session session = SftpTransferUtil.openSession(ip, port, username, password);
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String today = sdf.format(now);
				String command = "nohup java -jar " + workDir + "/" + pkg.getFileRealName() 
						+ " " + jobSetting.getJobName() + " >> " + 
						workDir + "/running_" + today + ".log &";
				log.info(ip + ":" + port + "(" + username + ")   " + workDir);
				log.info(command);
				SftpTransferUtil.runCmd(session, command, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	JobSetting job = jobSettingService.queryById(jobSetting.getId());
    	job.setId(jobSetting.getId());
    	job.setStatus(JOB_STATUS_OK); 
    	jobSettingService.updateStatus(job);
    	JobSettings settings = new JobSettings();
    	BeanUtils.copyProperties(job, settings);
    	jobAPIService.getJobSettingsAPI().updateJobSettings(settings);
    	json.put("success", true);
    	Date end = new Date();
    	log.info("==================作业【" +jobSetting.getJobName() + "】启动成功！" + (end.getTime() - start.getTime()) + "ms =================");
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return json.toJSONString();
    }
    
	private void distribute(String ip, int port, 
			String username, String password, String filePath,
			String targetPath, String fileName) {
		try {
			Session session = SftpTransferUtil.openSession(ip, port, username, password);
			SftpTransferUtil.transfer(session, filePath, targetPath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
