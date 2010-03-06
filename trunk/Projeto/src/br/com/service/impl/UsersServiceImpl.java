package br.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dao.UsersDao;
import br.com.model.User;
import br.com.service.UsersService;

@Component
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao usersDaoImpl;
	
	public List<User> findAll(){
		List<User> users = usersDaoImpl.findAll(User.class);
		return users;
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
