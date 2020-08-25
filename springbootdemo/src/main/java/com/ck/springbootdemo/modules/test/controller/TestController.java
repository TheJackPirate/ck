package com.ck.springbootdemo.modules.test.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ck.springbootdemo.modules.test.entity.City;
import com.ck.springbootdemo.modules.test.entity.Country;
import com.ck.springbootdemo.modules.test.service.CityService;
import com.ck.springbootdemo.modules.test.service.CountryService;
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
	
	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;
	
	@RequestMapping("/index")
	public String testIndex(ModelMap modelMap){
		int countryId = 522;
		Country country = countryService.getCountryByCountryId(countryId);
		List<City> cities = cityService.getCitiesByCountryId(countryId);
		
		modelMap.addAttribute("thymeleafTitle", "Title");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 99);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("baiduUrl", "/baidu");
		modelMap.addAttribute("city", cities.get(0));
		modelMap.addAttribute("shopLogo", "https://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("cities", cities.stream().limit(10).collect(Collectors.toList()));
		modelMap.addAttribute("updateCityUri", "/api/city");
		
		//将我们真正需要展示的页面路径包装到template属性中，由碎片组装器进行组装
		 modelMap.addAttribute("template","test/index"); 
		//直接返回外层碎片组装器classpath:/templates/index.html
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
	
	/**
	 * 127.0.0.1/test/desc?key=fuck
	 * @return
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc(HttpServletRequest request, @RequestParam String key) {
		String key2 = request.getParameter("key");
		return "This is test desc."+key+"----"+key2;
	}
}
