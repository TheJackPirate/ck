package com.ck.springbootdemo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "parameterFilter", urlPatterns = "/**")
public class ParameterFilter implements Filter{
	private final static Logger LOGGER = LoggerFactory.getLogger(ParameterFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("------------parameterFilter------------");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		//处理HttpServletRequest对象参数无法修改问题
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
			
			/**
			 * 获取HttpServletRequest对象参数并替换
			 */
			@Override
			public String getParameter(String name) {
				String value = httpRequest.getParameter(name);
				if(StringUtils.isNotBlank(value)) {
					return value.replaceAll("fuck", "***");
				}
				return super.getParameter(name);
			}

			/**
			 * 获取HttpServletRequest对象并替换@RequestParam获取到的参数
			 */
			@Override
			public String[] getParameterValues(String name) {
				String[] values = httpRequest.getParameterValues(name);
				if(values != null && values.length > 0) {
					values[0] = values[0].replace("fuck", "***");
					return values;
				}
				return super.getParameterValues(name);
			}
			
			
			
		};
		
		chain.doFilter(wrapper, response);
	}

}
