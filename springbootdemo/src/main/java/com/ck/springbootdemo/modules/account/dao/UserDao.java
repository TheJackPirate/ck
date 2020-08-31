package com.ck.springbootdemo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ck.springbootdemo.modules.account.entity.User;
import com.ck.springbootdemo.modules.common.vo.SearchVo;

@Mapper
public interface UserDao {
	@Insert("insert into user (user_name,password,create_date) "
			+ "values (#{userName},#{password},#{createDate})")
	void insertUser(User user);
	
	@Select("select * from user where user_name = #{userName}")
	User getUserByUserName(String userName);
	
	@Select("<script>" + 
			"select * from user "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ "and user_name like '%${keyWord}%' "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ "order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ "order by create_data desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<User> getUserBySearchVo(SearchVo searchVo);
	
	@Select("select * from user where user_id = #{userId}")
	@Results(id="userResult", value= {
			@Result(column="user_id", property="userId"),
			@Result(column="user_id",property="roles",
				javaType=List.class,
				many=@Many(select="com.ck.springbootdemo.modules.account.dao."
						+"RoleDao.getRolesByUserId"))
			
			})
	User getUserById(int userId);
	
	@Update("update user set user_name = #{userName} where user_id=#{userrId}")
	void updateUser(User user);
	
	@Delete("delete from user where user_id=#{userId}")
	void deleteUser(int userId);
}
