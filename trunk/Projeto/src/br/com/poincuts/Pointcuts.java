package br.com.poincuts;

import org.aspectj.lang.annotation.Pointcut;


public final class Pointcuts {

	private Pointcuts() {
	}

	@Pointcut("execution(* br.com.service.impl.*.*(..))")
	public void allServices() {}
	
	@Pointcut("execution(* br.com.dao.impl.*.*(..))")
	public void allDao() {}
}
