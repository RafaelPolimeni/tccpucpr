package br.com.aspects;

import javax.persistence.NoResultException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class ExceptionsAspect {
	@AfterThrowing(pointcut = "br.com.poincuts.Pointcuts.allDao()", throwing = "ex", argNames = "ex")
	public void noResultException(JoinPoint joinPoint, NoResultException ex)
			throws Throwable {

	}
}