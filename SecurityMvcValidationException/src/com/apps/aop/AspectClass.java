package com.apps.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.MappingException;
import org.springframework.aop.ThrowsAdvice;

@Aspect
public class AspectClass implements ThrowsAdvice{
	@AfterThrowing(pointcut="target(com.apps.controller.CartController)",throwing="ex")
	public void commonExceptionHandler(JoinPoint jp,Throwable ex){
		System.out.println("===============================================================");
		throw new MappingException("URL not mapped");
	}
}
