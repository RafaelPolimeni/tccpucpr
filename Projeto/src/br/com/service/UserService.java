package br.com.service;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public interface UserService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException;
}
