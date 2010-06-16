package br.com.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import br.com.exceptions.BaseDadosException;

@Service
@Aspect
public class ExceptionsAspect {
	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allDao()", throwing = "ex", argNames = "ex")
	public void exceptionDAO(JoinPoint joinPoint, Exception ex) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.error("Mensagem: " + ex.getMessage());
		logger.error("Causa: " + ex.getCause());

		BaseDadosException baseDadosException = new BaseDadosException(ex.getMessage(), ex.getCause());

		throw baseDadosException;
	}

	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allServices()", throwing = "ex", argNames = "ex")
	public void exceptionService(JoinPoint joinPoint, Exception ex) throws Throwable {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.error("Mensagem: " + ex.getMessage());
		logger.error("Causa: " + ex.getCause());
	}
}