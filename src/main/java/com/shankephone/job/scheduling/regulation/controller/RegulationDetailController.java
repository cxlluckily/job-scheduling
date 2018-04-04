package com.shankephone.job.scheduling.regulation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shankephone.job.scheduling.regulation.model.RegulationDetail;
import com.shankephone.job.scheduling.regulation.service.RegulationDetailService;

@Controller
@RequestMapping("/regulationDetail")
public class RegulationDetailController {
	
	@Resource
	private RegulationDetailService regulationDetailService;
	
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest req, HttpServletResponse res){
		List<RegulationDetail> list = regulationDetailService.list();
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public String query(RegulationDetail regulationDetail,HttpServletRequest request,HttpServletResponse response){
		RegulationDetail list = regulationDetailService.queryById(regulationDetail.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(RegulationDetail regulationDetail,HttpServletRequest request,HttpServletResponse response){
		Integer list = regulationDetailService.deleteById(regulationDetail.getId());
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(RegulationDetail regulationDetail,HttpServletRequest request,HttpServletResponse response) {
		Integer list = regulationDetailService.insert(regulationDetail);
		return JSONObject.toJSON(list).toString();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(RegulationDetail regulationDetail,HttpServletRequest request,HttpServletResponse response) {
		Integer list = regulationDetailService.update(regulationDetail);
		return JSONObject.toJSON(list).toString();
	}
	
}
