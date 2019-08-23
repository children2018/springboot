package com.spring.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP 切面
 * @author Administrator
 *
 */
@Aspect
@Component
@Slf4j
public class DataSourceAop {
	
	private Logger logger = Logger.getLogger(DataSourceAop.class);

	@Before("execution(* com.spring.springboot.mapper.*.select*(..)) || execution(* com.spring.springboot.mapper.*.find*(..)) || execution(* com.spring.springboot.mapper.*.query*(..)) || execution(* com.spring.springboot.mapper.*.get*(..))")
	public void setReadDataSourceType() {
		DataSourceContextHolder.read();
		logger.info("dataSource切换到：Read");
	}

	@Before("execution(* com.spring.springboot.mapper.*.insert*(..)) || execution(* com.spring.springboot.mapper.*.update*(..)) || execution(* com.spring.springboot.mapper.*.delete*(..))")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.write();
		logger.info("dataSource切换到：write");
	}
	
	@After("execution(* com.spring.springboot.mapper.*.select*(..)) || execution(* com.spring.springboot.mapper.*.find*(..))")
	public void clearReadDataSourceType() {
		DataSourceContextHolder.clear();
		logger.info("read之后清除本地标识");
	}

	@After("execution(* com.spring.springboot.mapper.*.insert*(..)) || execution(* com.spring.springboot.mapper.*.update*(..)) || execution(* com.spring.springboot.mapper.*.delete*(..))")
	public void clearWriteDataSourceType() {
		DataSourceContextHolder.clear();
		logger.info("write之后清除本地标识");
	}
	
	/*@Around("@annotation(org.springframework.transaction.annotation.Transactional)")
	public void setWriteDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
		Transactional datasource = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Transactional.class);
		if(datasource.readOnly()){
			DataSourceContextHolder.read();
			log.info("dataSource切换到：Read");
		}else{
			DataSourceContextHolder.write();
			log.info("dataSource切换到：write");
		}
		joinPoint.proceed();
	}*/
}
