package com.shankephone.job.scheduling.regulation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response){
		DBDataSource list = dataSourceService.queryById(dBDataSource.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response){
		Integer list = dataSourceService.deleteById(dBDataSource.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response) {
		Integer list = dataSourceService.insert(dBDataSource);
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(DBDataSource dBDataSource,HttpServletRequest request,HttpServletResponse response) {
		Integer list = dataSourceService.update(dBDataSource);
		return JSONObject.toJSON(list).toString();
	}
	
}
