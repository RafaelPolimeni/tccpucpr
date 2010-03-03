package br.com.dao;

import br.com.model.User;

public interface UserDao {
	User findUser(String userName);
}
