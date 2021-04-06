package com.immobel.logging.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAdvice {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.immobel.service..*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("Start :: {} | Args => {}", joinPoint.getSignature(),Arrays.asList(joinPoint.getArgs()));
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("End :: {} and executed in {} ms", joinPoint.getSignature(), executionTime);

		return proceed;
	}

}
