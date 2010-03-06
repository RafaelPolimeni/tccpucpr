package br.com.dao;

import br.com.model.User;

public interface UserDao extends CrudDAO<User>{
	User findUserByName(String userName);
}
