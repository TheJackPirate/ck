package com.ck.springbootdemo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.springbootdemo.modules.account.entity.Role;
import com.ck.springbootdemo.modules.account.service.RoleService;

@Controller
@RequestMapping("/account")
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@RequestMapping("roles")
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
}
