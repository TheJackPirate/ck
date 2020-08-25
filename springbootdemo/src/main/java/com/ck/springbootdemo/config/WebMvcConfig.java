package com.ck.springbootdemo.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ck.springbootdemo.filter.ParameterFilter;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig {
	/**
	 * 注册参数过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<ParameterFilter> parameterFilter(){
		FilterRegistrationBean<ParameterFilter> registration = new FilterRegistrationBean<ParameterFilter>();
		registration.setFilter(new ParameterFilter());
		return registration;
	}
}
