package com.ck.springbootdemo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ck.springbootdemo.modules.account.dao.UserDao;
import com.ck.springbootdemo.modules.account.dao.UserRoleDao;
import com.ck.springbootdemo.modules.account.entity.Role;
import com.ck.springbootdemo.modules.account.entity.User;
import com.ck.springbootdemo.modules.account.service.UserService;
import com.ck.springbootdemo.modules.common.vo.Result;
import com.ck.springbootdemo.modules.common.vo.Result.ResultStatus;
import com.ck.springbootdemo.modules.common.vo.SearchVo;
import com.ck.springbootdemo.utils.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	@Transactional
	public Result<User> insertUser(User user) {
		Result<User> result = new Result<User>(ResultStatus.SUCCESS.status,"");
		
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if(userTemp != null) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage("User name is repeat");
			return result;
		}
		
		user.setCreateDate(new Date());
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		userDao.insertUser(user);
		
		List<Role> roles = user.getRoles();
		if(roles != null) {
			userRoleDao.deleteUserRoleByUserId(user.getUserId());
			for(Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		return result;
	}

	@Override
	public Result<User> login(User user) {
//		User userTemp = userDao.getUserByUserName(user.getUserName());
//		if(userTemp == null || !userTemp.getPassword().equals(MD5Util.getMD5((user.getPassword())))) {
//			return new Result<User>(ResultStatus.FAILED.status,"User name or password is error.");
//		}else {
//			return new Result<User>(ResultStatus.SUCCESS.status,"");
//		}
		
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword()));
		usernamePasswordToken.setRememberMe(user.getRememberMe());
		
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(usernamePasswordToken);
		}catch(Exception e) {
			e.printStackTrace();
			return new Result<User>(ResultStatus.FAILED.status,"User name or password is error");
		}
		return new Result<User>(ResultStatus.SUCCESS.status,"");
	}

	@Override
	public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<User>(
				Optional.ofNullable(userDao.getUserBySearchVo(searchVo))
				.orElse(Collections.emptyList()));
	}

	@Override
	public User getUserById(int userId) {
		
		return userDao.getUserById(userId);
	}

	@Override
	@Transactional
	public Result<User> updateUser(User user) {
		User userTemp = userDao.getUserByUserName(user.getUserName());
		if(userTemp != null && user.getUserId() != userTemp.getUserId()) {
			return new Result<User>(ResultStatus.FAILED.status,"User name is repeat.");
		}
		
		userDao.updateUser(user);
		List<Role> roles = user.getRoles();
		if(!roles.isEmpty()) {
			userRoleDao.deleteUserRoleByUserId(user.getUserId());
			for(Role role : roles) {
				userRoleDao.addUserRole(role.getRoleId(), user.getUserId());
			}
		}
		return new Result<User>(ResultStatus.SUCCESS.status,"");
	}

	@Override
	public Result<User> deleteUser(int userId) {
		userDao.deleteUser(userId);
		return new Result<User>(ResultStatus.SUCCESS.status,"");
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

}
