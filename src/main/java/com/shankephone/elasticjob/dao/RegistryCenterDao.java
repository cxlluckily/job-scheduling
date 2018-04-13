package com.shankephone.elasticjob.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shankephone.elasticjob.model.RegistryCenter;

public interface RegistryCenterDao {
	
	public RegistryCenter queryById(@Param("id")Long id);
	
	public Integer insert(RegistryCenter source);

	public Integer update(RegistryCenter source);

	public Integer deleteById(@Param("id")Long id);

	public List<RegistryCenter> queryList();  
	
	public RegistryCenter loadActivated();

	public RegistryCenter find(@Param("name")String name);

	public void updateActivated(@Param("id")Long id); 

}
