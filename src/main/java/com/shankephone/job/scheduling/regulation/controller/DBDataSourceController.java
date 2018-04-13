package com.shankephone.job.scheduling.regulation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shankephone.job.scheduling.common.HttpRequestWraper;
import com.shankephone.job.scheduling.regulation.model.DBDataSource;
import com.shankephone.job.scheduling.regulation.service.DBDataSourceService;

@Controller
@RequestMapping("/dataSource")
public class DBDataSourceController {
	
	@Resource
	private DBDataSourceService dataSourceService;
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest req, HttpServletResponse res){
		List<DBDataSource> list = dataSourceService.list();
		String json = JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString();
		res.setContentType("text/json; charset=utf-8");
		return json;
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response){
		DBDataSource list = dataSourceService.queryById(dBDataSource.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		Integer count = dataSourceService.deleteById(id);
		JSONObject json = new JSONObject();
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}
		json.put("success", false);
		return json.toJSONString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(HttpServletRequest request,HttpServletResponse response) {
		DBDataSource dBDataSource = HttpRequestWraper.generateModel(request, DBDataSource.class);
		Integer count =  dataSourceService.insert(dBDataSource);
		JSONObject json = new JSONObject();
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}
		json.put("success", false);
		return json.toJSONString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request,HttpServletResponse response) {
		DBDataSource dBData = HttpRequestWraper.generateModel(request, DBDataSource.class);
		Integer count =  dataSourceService.update(dBData);
		JSONObject json = new JSONObject();
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}
		json.put("success", false);
		return json.toJSONString();
	}
	
}
