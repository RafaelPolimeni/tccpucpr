package br.com.dao;

import java.util.List;

import br.com.model.Authority;
import br.com.model.User;

public interface UsersDao extends CrudDAO<User> {
	
	List<Authority> findAllAuthorities();
	
}
