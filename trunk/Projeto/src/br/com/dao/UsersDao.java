package br.com.dao;

import java.util.List;

import br.com.model.Authority;
import br.com.model.User;

public interface UsersDao extends CrudDao<User> {
	
	List<Authority> findAllAuthorities();
	
}
