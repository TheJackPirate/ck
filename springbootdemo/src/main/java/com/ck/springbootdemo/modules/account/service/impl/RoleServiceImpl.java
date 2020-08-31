package com.ck.springbootdemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.springbootdemo.modules.account.dao.RoleDao;
import com.ck.springbootdemo.modules.account.entity.Role;
import com.ck.springbootdemo.modules.account.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao roleDao;
	
	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	@Override
	public List<Role> getRoleByUserId(int userId) {
		return roleDao.getRoleByUserId(userId);
	}

}
