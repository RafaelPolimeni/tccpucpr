package br.com.service;

import java.util.List;

import br.com.model.Authority;
import br.com.model.User;

public interface UsersService {
	List<User> findAll();
	
	List<Authority> findAllAuthorities();
}
