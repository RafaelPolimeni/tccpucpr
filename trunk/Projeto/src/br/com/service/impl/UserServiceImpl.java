package br.com.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.dao.UserDao;
import br.com.model.Authority;
import br.com.service.UserService;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDao userDaoImpl;

	public UserServiceImpl() {
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		br.com.model.User user = userDaoImpl.findUserByName(userName);

		if (user == null){
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("userNotFound", "userNotFound");
			return null;
		}else {
			return makeUser(user);
		}
	}

	private User makeUser(br.com.model.User user) {
 		return new User(user.getUserName(), user.getPassword(), true, true, true, true, makeGrantedAuthorities(user));
	}

	private Collection<GrantedAuthority> makeGrantedAuthorities(br.com.model.User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthorityImpl(user.getAuthority().getName()));
		
		return authorities;
	}

	public br.com.model.User findByUsername(String userName) {
		br.com.model.User user = userDaoImpl.findUserByName(userName);
		return user;
	}
}