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
import com.shankephone.job.scheduling.regulation.model.Regulation;
import com.shankephone.job.scheduling.regulation.service.RegulationService;

@Controller
@RequestMapping("/regulation")
public class RegulationController {
	
	@Resource
	private RegulationService regulationService;
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest req, HttpServletResponse res){
		List<Regulation> list = regulationService.list();
		String json = JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss").toString();
		res.setContentType("text/json; charset=utf-8");
		return json;
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
		String id = request.getParameter("id");
		Integer count = regulationService.deleteById(id);
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
		Regulation regulation = HttpRequestWraper.generateModel(request, Regulation.class);
		Integer count = regulationService.insert(regulation);
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
		Regulation reg = HttpRequestWraper.generateModel(request, Regulation.class);
		Integer count = regulationService.update(reg);
		JSONObject json = new JSONObject();
		if(count > 0){
			json.put("success", true);
			return json.toJSONString();
		}
		json.put("success", false);
		return json.toJSONString();
	}
	
}
