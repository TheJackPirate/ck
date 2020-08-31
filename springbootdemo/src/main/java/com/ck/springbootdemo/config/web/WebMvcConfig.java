package com.ck.springbootdemo.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ck.springbootdemo.filter.ParameterFilter;
import com.ck.springbootdemo.interceptor.UrlInterceptor;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	private UrlInterceptor urlInterceptor;
	
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

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	

}
