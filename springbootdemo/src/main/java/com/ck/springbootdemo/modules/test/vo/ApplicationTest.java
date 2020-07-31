package com.ck.springbootdemo.modules.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:applicationTest.properties")
@ConfigurationProperties(prefix = "com.test")
public class ApplicationTest {
	private String name;
	private int age;
	private String description;
	private String random;
	public ApplicationTest(String name, int age, String description, String random) {
		super();
		this.name = name;
		this.age = age;
		this.description = description;
		this.random = random;
	}
	public ApplicationTest() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	@Override
	public String toString() {
		return "ApplicationTest [name=" + name + ", age=" + age + ", description=" + description + ", random=" + random
				+ "]";
	}
	
	
}
