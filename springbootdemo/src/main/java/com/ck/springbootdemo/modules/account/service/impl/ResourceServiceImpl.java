package com.ck.springbootdemo.modules.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ck.springbootdemo.modules.account.dao.ResourceDao;
import com.ck.springbootdemo.modules.account.entity.Resource;
import com.ck.springbootdemo.modules.account.service.ResourceService;

public class ResourceServiceImpl implements ResourceService{

	@Autowired
	ResourceDao resourceDao;
	
	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		return resourceDao.getResourcesByRoleId(roleId);
	}

}
