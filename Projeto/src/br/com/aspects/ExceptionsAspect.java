package br.com.aspects;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class ExceptionsAspect {
	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allDao()", throwing = "ex", argNames = "ex")
	public void noResultException(JoinPoint joinPoint, NoResultException ex) throws Throwable {
		System.out.println("OPA");
	}

	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allDao()", throwing = "ex", argNames = "ex")
	public void dataIntegrityViolationException(JoinPoint joinPoint, Exception ex) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.error(ex.getMessage());
		logger.error(ex.getStackTrace());
	}
	
	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allServices()", throwing = "ex", argNames = "ex")
	public void constraintViolationException(JoinPoint joinPoint, DataIntegrityViolationException ex) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.error(ex.getMessage());
		logger.error(ex.getStackTrace());
	}
}