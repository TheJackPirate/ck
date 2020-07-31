package com.ck.springbootdemo.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.springbootdemo.modules.test.vo.ApplicationTest;

@Controller
@RequestMapping("/test")
public class TestController {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Value("${server.port}")
	private int port;
	@Value("${com.ck.name}")
	private String name;
	@Value("${com.ck.age}")
	private int age;
	@Value("${com.ck.description}")
	private String desc;
	@Value("${com.ck.random}")
	private String random;
	
	@Autowired
	private ApplicationTest applicationTest;
	
	@RequestMapping("/index")
	public String testIndex(){
		return "index";
	}
	
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		LOGGER.trace("This is trace log");
		LOGGER.debug("This is debug log");
		LOGGER.info("This is info log");
		LOGGER.warn("This is warn log");
		LOGGER.error("This is error log");
		return "This is log test .";
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String configTest(){
		StringBuffer sb = new StringBuffer();
		sb.append(port).append(" , ")
			.append(name).append(" , ")
			.append(age).append(" , ")
			.append(desc).append(" , ")
			.append(random).append(" , ")
			.append(applicationTest.toString());
		return sb.toString();
	}
	
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc() {
		return "This is test desc";
	}
}
