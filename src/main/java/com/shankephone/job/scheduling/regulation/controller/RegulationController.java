package com.shankephone.job.scheduling.regulation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(Regulation regulation,HttpServletRequest request,HttpServletResponse response){
		Regulation list = regulationService.queryById(regulation.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Regulation regulation,HttpServletRequest request,HttpServletResponse response){
		Integer list = regulationService.deleteById(regulation.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(Regulation regulation,HttpServletRequest request,HttpServletResponse response) {
		Integer list = regulationService.insert(regulation);
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(Regulation regulation,HttpServletRequest request,HttpServletResponse response) {
		Integer list = regulationService.update(regulation);
		return JSONObject.toJSON(list).toString();
	}
	
}
