package br.com.bean;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import br.com.model.Authority;
import br.com.service.UserService;

public class UserBean {
	private Integer idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean enable;
	private Authority authority;

	@Autowired
	private UserService userServiceImpl;

	public String login() throws Exception {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
 
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");

		dispatcher.forward((HttpServletRequest) context.getRequest(), (HttpServletResponse) context.getResponse());

		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
				AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		if (e == null && session.getAttribute("userNotFound") == null) {
			BeanUtils.copyProperties(this, userServiceImpl.findByUsername(((HttpServletRequest) context.getRequest()).getParameter("j_username")));
		} 
		FacesContext.getCurrentInstance().responseComplete();
		// It's OK to return null here because Faces is just going to exit.

		return null;
	}

	/**
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the userServiceImpl
	 */
	public UserService getUserServiceImpl() {
		return userServiceImpl;
	}

	/**
	 * @param userServiceImpl
	 *            the userServiceImpl to set
	 */
	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	/**
	 * @return the authority
	 */
	public Authority getAuthority() {
		return authority;
	}

	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
}
