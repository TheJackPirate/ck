package com.ck.springbootdemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ck.springbootdemo.modules.account.entity.Role;

@Mapper
public interface RoleDao {
	@Select("select * from role")
	List<Role> getRoles();
	
	@Select("select * from role role left join user_role userRole "
	+ "on role.role_id = userRole.role_id where userRole.user_id = #{userId})")
	List<Role> getRoleByUserId(int userId);
}
