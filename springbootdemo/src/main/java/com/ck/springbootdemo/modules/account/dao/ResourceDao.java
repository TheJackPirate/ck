package com.ck.springbootdemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ck.springbootdemo.modules.account.entity.Resource;

@Mapper
public interface ResourceDao {
	@Select("select * from resource resource left join role_resource roleResource "
	+ "on resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId})")
	List<Resource> getResourcesByRoleId(int roleId);
}
