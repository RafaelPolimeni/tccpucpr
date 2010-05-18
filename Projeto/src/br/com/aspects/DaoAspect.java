package br.com.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class DaoAspect {

	@Before("br.com.poincuts.Pointcuts.allDao()")
	public void before(JoinPoint joinPoint) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info("Iniciando - " + joinPoint.getSignature());
	}

	@After("br.com.poincuts.Pointcuts.allDao()")
	public void after(JoinPoint joinPoint) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info("Finalizando - " + joinPoint.getSignature());
	}
}