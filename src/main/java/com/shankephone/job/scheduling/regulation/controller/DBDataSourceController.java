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
import com.shankephone.job.scheduling.regulation.service.DBDataSourceService;
import com.shankephone.job.scheduling.regulation.service.RegulationService;

@Controller
@RequestMapping("/dataSource")
public class DBDataSourceController {
	
	@Resource
	private DBDataSourceService dataSourceService;
	@Resource
	private RegulationService regulationService;
	
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response){
		DBDataSource list = dataSourceService.queryById(dBDataSource.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		String id = request.getParameter("id");
		long regCount=regulationService.queryCountByDataSource(Long.parseLong(id));
		if(regCount>0){
			json.put("success", false);
			json.put("msg", "请先删除使用该数据源的规则！");
			return json.toJSONString();
		}
		Integer count = dataSourceService.deleteById(id);
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
		JSONObject json = new JSONObject();
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		DBDataSource dBDataSource = HttpRequestWraper.generateModel(request, DBDataSource.class);
		if(dBDataSource==null || "".equals(dBDataSource.getMaster_url())
				|| "".equals(dBDataSource.getMaster_username()) || "".equals(dBDataSource.getMaster_password())
				|| ("1".equals(dBDataSource.getSource_type())&&("".equals(dBDataSource.getSlave_url())
				|| "".equals(dBDataSource.getSlave_username()) || "".equals(dBDataSource.getSlave_password())))){
			json.put("success", false);
			json.put("msg", "请检查表单是否填写完整！");
			return json.toJSONString();
		}
		if(dBDataSource.getSlave_url().equals(dBDataSource.getMaster_url())){
			json.put("success", false);
			json.put("msg", "主/从库不能相同！");
			return json.toJSONString();
		}
		Integer count =  dataSourceService.insert(dBDataSource);
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
		JSONObject json = new JSONObject();
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		DBDataSource dBData = HttpRequestWraper.generateModel(request, DBDataSource.class);
		if(dBData==null || "".equals(dBData.getMaster_url())
				|| "".equals(dBData.getMaster_username()) || "".equals(dBData.getMaster_password())
				|| ("1".equals(dBData.getSource_type())&&("".equals(dBData.getSlave_url())
				|| "".equals(dBData.getSlave_username()) || "".equals(dBData.getSlave_password())))){
			json.put("success", false);
			json.put("msg", "请检查表单是否填写完整！");
			return json.toJSONString();
		}
		if(dBData.getSlave_url().equals(dBData.getMaster_url())){
			json.put("success", false);
			json.put("msg", "主/从库不能相同！");
			return json.toJSONString();
		}
		Integer count =  dataSourceService.update(dBData);
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}else{
			json.put("success", false);
			json.put("msg", "更新失败！");
		}
		return json.toJSONString();
	}
	
	
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
        
        List<DBDataSource> list = dataSourceService.list(Integer.parseInt(start), Integer.parseInt(limit));
        long count = dataSourceService.queryTotalCount();
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString());
        JSONObject json = new JSONObject();
        json.put("list", array);
        json.put("totalCount", count);
        response.setContentType("text/json; charset=utf-8");
        return json.toJSONString();
    }
	
	@RequestMapping("/cleanList")
	@ResponseBody
	public String cleanList(HttpServletRequest req, HttpServletResponse res){
		List<DBDataSource> list = dataSourceService.cleanList();
		String json = JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString();
		res.setContentType("text/json; charset=utf-8");
		return json;
	}
	
	@RequestMapping("/historyList")
	@ResponseBody
	public String historyList(HttpServletRequest req, HttpServletResponse res){
		List<DBDataSource> list = dataSourceService.historyList();
		String json = JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString();
		res.setContentType("text/json; charset=utf-8");
		return json;
	}
}
