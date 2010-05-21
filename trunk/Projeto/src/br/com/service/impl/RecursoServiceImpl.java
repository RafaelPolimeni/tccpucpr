package br.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.UsersDao;
import br.com.model.Autoridade;
import br.com.model.Recurso;
import br.com.service.RecursoService;

@Service
public class RecursoServiceImpl implements RecursoService{
	
	@Autowired
	private UsersDao usersDaoImpl;
	
	public List<Recurso> findAll(){
		return usersDaoImpl.findAll(Recurso.class);
	}
	
	public List<Autoridade> findAllAuthorities(){
		return usersDaoImpl.findAllAuthorities();
	}
	/**
	 * @return the usersDaoImpl
	 */
	public UsersDao getUsersDaoImpl() {
		return usersDaoImpl;
	}
	/**
	 * @param usersDaoImpl the usersDaoImpl to set
	 */
	public void setUsersDaoImpl(UsersDao usersDaoImpl) {
		this.usersDaoImpl = usersDaoImpl;
	}
}
