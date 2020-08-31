package com.ck.springbootdemo.modules.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ck.springbootdemo.modules.account.entity.Role;


public interface RoleService {
	List<Role> getRoles();
	List<Role> getRoleByUserId(int userId);
}
