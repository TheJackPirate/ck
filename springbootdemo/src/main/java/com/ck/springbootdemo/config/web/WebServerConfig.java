package com.ck.springbootdemo.config.web;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebServerConfig {

	@Value("${http.port}")
	private int httpPort;
	
	/**
	 * Http Connector
	 * @return
	 */
	@Bean
	public Connector connector() {
		Connector connector = new Connector();
		connector.setScheme("http");
		connector.setPort(httpPort);
		return connector;
	}
	
	/**
	 * 1、重新注册ServletWebServerFactory bean
	 * 2、以TomcatServletWebServerFactory实现，添加http连接器
	 * @return
	 */
	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(connector());
		return tomcat;
	}
}
