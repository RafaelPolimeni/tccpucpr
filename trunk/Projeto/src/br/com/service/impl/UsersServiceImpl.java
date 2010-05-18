package br.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dao.UsersDao;
import br.com.model.Authority;
import br.com.model.User;
import br.com.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao usersDaoImpl;
	
	public List<User> findAll(){
		return usersDaoImpl.findAll(User.class);
	}
	
	public List<Authority> findAllAuthorities(){
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
