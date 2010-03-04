package br.com.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.model.User;

public interface UserService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
	
	User find(String userName);
}
