package com.ck.springbootdemo.modules.account.controller;

import org.springframework.stereotype.Controller;
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
	
}
