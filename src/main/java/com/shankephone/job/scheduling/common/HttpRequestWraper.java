package com.shankephone.job.scheduling.common;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.shankephone.job.scheduling.regulation.model.BaseModel;

public class HttpRequestWraper{
	
	public static <T extends BaseModel> T generateModel(HttpServletRequest request, Class<T> clazz){
		String params = request.getParameter("params");
		T t = JSONObject.parseObject(params, clazz);
		return t;
	}
	
	public static void main(String[] args) {
		String str = "{\"db_name\":\"as\",\"table_name\":\"dd\",\"source_id\":\"11\"}";
		JSONObject json = JSONObject.parseObject(str);
		System.out.println(json.toJSONString());
	}
	
}