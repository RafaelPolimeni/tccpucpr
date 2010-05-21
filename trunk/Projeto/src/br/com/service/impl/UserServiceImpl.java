package br.com.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.dao.UserDao;
import br.com.model.Recurso;
import br.com.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDao userDaoImpl;

	public UserServiceImpl() {
	}

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		Recurso recurso = null;
		
		try{
			recurso = userDaoImpl.findUserByName(userName);
		}catch (NoResultException e) {
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("userNotFound", "userNotFound");
			return null;
		}

		return makeUser(recurso);
	}

	private User makeUser(Recurso recurso) {
 		return new User(recurso.getUsuario(), recurso.getSenha(), true, true, true, true, makeGrantedAuthorities(recurso));
	}

	private Collection<GrantedAuthority> makeGrantedAuthorities(Recurso recurso) {
		Collection<GrantedAuthority> autoridades = new ArrayList<GrantedAuthority>();

		autoridades.add(new GrantedAuthorityImpl(recurso.getAutoridade().getNome()));
		
		return autoridades;
	}

	public br.com.model.Recurso findByUsername(String userName) {
		br.com.model.Recurso user = userDaoImpl.findUserByName(userName);
		return user;
	}
}