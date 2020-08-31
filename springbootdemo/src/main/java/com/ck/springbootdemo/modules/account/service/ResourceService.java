package com.ck.springbootdemo.modules.account.service;

import java.util.List;

import com.ck.springbootdemo.modules.account.entity.Resource;

public interface ResourceService {
	public List<Resource> getResourcesByRoleId(int roleId);
}
