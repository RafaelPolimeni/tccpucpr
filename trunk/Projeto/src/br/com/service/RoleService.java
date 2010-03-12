package br.com.service;

import java.util.List;

import br.com.model.Role;

public interface RoleService {
	List<Role> findAll();
	
	void save(Role role);
	
	void update(Role role);
	
	void delete(Role role);
}

