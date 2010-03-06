package br.com.bean;

import java.util.List;

import br.com.model.User;
import br.com.service.UsersService;

public class UsersBean {
	
	private UsersService usersServiceImpl;
	private List<User> users; 
	
	public String findAll(){
		setUsers(usersServiceImpl.findAll());
		
		return "users";
	}
	
	/**
	 * @return the usersServiceImpl
	 */
	public UsersService getUsersServiceImpl() {
		return usersServiceImpl;
	}

	/**
	 * @param usersServiceImpl the usersServiceImpl to set
	 */
	public void setUsersServiceImpl(UsersService usersServiceImpl) {
		this.usersServiceImpl = usersServiceImpl;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
