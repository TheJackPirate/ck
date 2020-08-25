package com.ck.springbootdemo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ControllerAspect {
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	/**
	  *  第一个*表示返回类型不限
	 * 第二个*表示modules包下所有的子包
	 * 第三个*表示所有类
	 * 第四个*表示所有方法
	 * (..)代表参数不同
	 * Order 代表优先级，数字越小优先级越高    
	 */
	@Pointcut("execution(public * com.ck.springbootdemo.modules.*.controller.*.*(..))")
	@Order(1)
	public void controllerPointCut() {
		
	}
	
	//前置通知
	@Before(value = "com.ck.springbootdemo.aspect.ControllerAspect.controllerPointCut()")
	public void beforeController(JoinPoint joinPoint) {
		LOGGER.info("========");
		LOGGER.info("Before controller");
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LOGGER.info("请求来源："+request.getRemoteAddr());
		LOGGER.debug("请求URL：" + request.getRequestURL().toString());
		LOGGER.debug("请求方式：" + request.getMethod());
		LOGGER.debug("响应方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + 
		                joinPoint.getSignature().getName());
		LOGGER.debug("请求参数：" + Arrays.toString(joinPoint.getArgs()));

	}
	
	/**
	 * 环绕通知 = 前置 + 目标方法执行 + 后置通知
	 * ProceedingJoinPoint 继承了 JoinPoint，是在 JoinPoint 的基础上暴露出  proceed 这个方法，
	 * -这个是 aop 代理链执行的方法， 执行proceed方法的作用是让目标方法执行，这也是环绕通知和前置、后置通知方法的一个最大区别;
	 */
	@Around(value="com.ck.springbootdemo.aspect.ControllerAspect.controllerPointCut()")
	public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LOGGER.info("Around controller");
		return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
	}
	
	/**
	 * 后置通知
	 */
	@After(value="com.ck.springbootdemo.aspect.ControllerAspect.controllerPointCut()")
	public void afterController() {
        LOGGER.info("After Controller.");
        LOGGER.info("=======================");

	}
}
