package br.com.service.impl;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.dao.DataAccessException;

import br.com.bean.UserBean;
import br.com.dao.UserDao;
import br.com.dao.impl.UserDaoImpl;
import br.com.model.Authority;
import br.com.service.UserService;

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
			User acegiUser = makeAcegiUser(user);

			try {
				UserBean userBean = (UserBean) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true))
						.getAttribute("userBean");
				BeanUtils.copyProperties(userBean, user);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return acegiUser;
		}
	}

	private User makeAcegiUser(br.com.model.User user) {
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
}