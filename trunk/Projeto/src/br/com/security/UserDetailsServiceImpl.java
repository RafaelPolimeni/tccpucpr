package br.com.security;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public class UserDetailsServiceImpl implements UserDetailsService {
	private UserDaoImpl userDao;

	public UserDetailsServiceImpl(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		AppUser user = userDao.findUser(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found: " + username);
		else {
			return makeAcegiUser(user);
		}
	}

	private User makeAcegiUser(AppUser user) {
		return new User(user.getLogin(), user.getPassword(), true, true, true, true, makeGrantedAuthorities(user));
	}

	private GrantedAuthority[] makeGrantedAuthorities(AppUser user) {
		GrantedAuthority[] result = new GrantedAuthority[user.getRoles().size()];
		int i = 0;
		for (String role : user.getRoles()) {
			result[i++] = new GrantedAuthorityImpl(role);
		}
		return result;
	}
}