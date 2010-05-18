package br.com.dao;

import br.com.model.User;

public interface UserDao extends CrudDao<User>{
	User findUserByName(String userName);
}
