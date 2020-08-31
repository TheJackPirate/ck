package com.ck.springbootdemo.modules.account.service;

import com.ck.springbootdemo.modules.account.entity.User;
import com.ck.springbootdemo.modules.common.vo.Result;
import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

public interface UserService {
	public Result<User> insertUser(User user);
	
	public Result<User> login(User user);
	
	PageInfo<User> getUserBySearchVo(SearchVo searchVo);
	
	public User getUserById(int userId);
	
	public Result<User> updateUser(User user);
	
	public Result<User> deleteUser(int userId);
	
	public User getUserByUserName(String userName);
}
