package com.shankephone.job.scheduling.regulation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shankephone.job.scheduling.common.HttpRequestWraper;
import com.shankephone.job.scheduling.regulation.model.DBDataSource;
import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.model.JobRegRelation;
import com.shankephone.job.scheduling.regulation.service.DBDataSourceService;
import com.shankephone.job.scheduling.regulation.service.RegulationService;

@Controller
@RequestMapping("/regulation")
public class RegulationController {
	
	@Resource
	private RegulationService regulationService;
	@Resource
	private DBDataSourceService dBDataSourceService;
	
	@RequestMapping("/list")
	@ResponseBody
    public String list( HttpServletRequest request,HttpServletResponse response) {
    	String start = request.getParameter("start");
    	String limit = request.getParameter("limit");
       
        if(start == null || "".equals(start)){
        	start = "0";
        }
        if(limit == null || "".equals(limit)){
        	limit = "5";
        }
        
        List<Regulation> list = regulationService.list(Integer.parseInt(start), Integer.parseInt(limit));
        long count = regulationService.queryTotalCount();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString());
        JSONObject json = new JSONObject();
        json.put("list", array);
        json.put("totalCount", count);
        response.setContentType("text/json; charset=utf-8");
        return json.toJSONString();
    }
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Regulation regulation,HttpServletRequest request,HttpServletResponse response){
		Regulation list = regulationService.queryById(regulation.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		String id = request.getParameter("id");
		long jobCount=regulationService.queryCountByJob(Long.parseLong(id));
		if(jobCount>0){
			json.put("success", false);
			json.put("msg", "请先删除使用规则的作业！");
			return json.toJSONString();
		}
		Integer count = regulationService.deleteById(id);
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}else{
			json.put("success", false);
			json.put("msg", "删除失败！");
		}
		return json.toJSONString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		JSONObject json = new JSONObject();
		Regulation regulation = HttpRequestWraper.generateModel(request, Regulation.class);
		if(regulation==null || "".equals(regulation.getSource_id()) || "".equals(regulation.getHistory_source_id())
				|| "".equals(regulation.getDb_name()) || "".equals(regulation.getHistory_db_name())
				|| "".equals(regulation.getCheck_sql()) || "".equals(regulation.getCheck_history_sql())
				|| "".equals(regulation.getSql_txt()) || "".equals(regulation.getName())){
			json.put("success", false);
			json.put("msg", "请检查表单是否填写完整！");
			return json.toJSONString();
		}
		if(regulation!=null && regulation.getSource_id() != null && regulation.getHistory_source_id() != null){
			DBDataSource dbDataSource=dBDataSourceService.queryById(regulation.getSource_id());
			DBDataSource hisDbDataSource=dBDataSourceService.queryById(regulation.getHistory_source_id());
			if((dbDataSource.getSlave_url().equals(hisDbDataSource.getMaster_url()) || dbDataSource.getMaster_url().equals(hisDbDataSource.getMaster_url()))
					&& regulation.getDb_name().equals(regulation.getHistory_db_name())){
				json.put("success", false);
				json.put("msg", "新增失败，历史库信息与清理库（主/从）信息相同！");
				return json.toJSONString();
			}
		}
		Integer count = regulationService.insert(regulation);
		
		JobRegRelation jobRegRelation = new JobRegRelation();
		long reg_id =regulationService.queryMaxId();
		jobRegRelation.setReg_id(reg_id);
		String params = request.getParameter("params");
		JSONObject jsona = JSON.parseObject(params);
		String jobId = String.valueOf(jsona.get("id_job_setting"));
		jobRegRelation.setId_job_setting(Long.parseLong(jobId));
		regulationService.insertJob(jobRegRelation);
		
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}else{
			json.put("success", false);
			json.put("msg", "新增失败！");
		}
		return json.toJSONString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		JSONObject json = new JSONObject();
		Regulation reg = HttpRequestWraper.generateModel(request, Regulation.class);
		if(reg==null || "".equals(reg.getSource_id()) || "".equals(reg.getHistory_source_id())
				|| "".equals(reg.getDb_name()) || "".equals(reg.getHistory_db_name())
				|| "".equals(reg.getCheck_sql()) || "".equals(reg.getCheck_history_sql())
				|| "".equals(reg.getSql_txt()) || "".equals(reg.getName())){
			json.put("success", false);
			json.put("msg", "请检查表单是否填写完整！");
			return json.toJSONString();
		}
		if(reg!=null && reg.getSource_id() != null && reg.getHistory_source_id() != null){
			DBDataSource dbDataSource=dBDataSourceService.queryById(reg.getSource_id());
			DBDataSource hisDbDataSource=dBDataSourceService.queryById(reg.getHistory_source_id());
			if((dbDataSource.getSlave_url().equals(hisDbDataSource.getMaster_url()) || dbDataSource.getMaster_url().equals(hisDbDataSource.getMaster_url()))
					&& reg.getDb_name().equals(reg.getHistory_db_name())){
				json.put("success", false);
				json.put("msg", "更新失败，历史库信息与清理库（主/从）信息相同！");
				return json.toJSONString();
			}
		}
		Integer count = regulationService.update(reg);
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}else{
			json.put("success", false);
			json.put("msg", "更新失败！");
		}
		return json.toJSONString();
	}
	
}
