package br.com.services.impl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.bean.UserBean;
import br.com.dao.UserDao;
import br.com.dao.impl.UserDaoImpl;
import br.com.model.Authority;
import br.com.services.UserService;

public class UserServiceImpl implements UserService, UserDetailsService {
	private UserDao userDao;

	public UserServiceImpl(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		br.com.model.User user = userDao.findUser(username);

		if (user == null)
			throw new UsernameNotFoundException("User not found: " + username);
		else {
			return makeUser(user);
		}
	}

	private User makeUser(br.com.model.User user) {
		return new User(user.getUserName(), user.getPassword(), true, true, true, true, makeGrantedAuthorities(user));
	}

	private GrantedAuthority[] makeGrantedAuthorities(br.com.model.User user) {
		GrantedAuthority[] result = new GrantedAuthority[user.getAuthorities().size()];
		int i = 0;
		for (Authority authority : user.getAuthorities()) {
			result[i++] = new GrantedAuthorityImpl(authority.getName());
		}
		return result;
	}
	
	public br.com.model.User find(String username){
		br.com.model.User user = userDao.findUser(username);
		return user;
	}
}