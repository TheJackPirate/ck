package com.ck.springbootdemo.modules.account.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping("/register")
	public String registerPage() {
		return "indexSimple";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}
	
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}
	
	@RequestMapping("/logout")
	public String loginOut(ModelMap modelMap) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		modelMap.addAttribute("template","account/login");
		return "indexSimple";
	}
	
}
