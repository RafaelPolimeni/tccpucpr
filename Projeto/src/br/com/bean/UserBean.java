package br.com.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.ui.webapp.AuthenticationProcessingFilter;
import org.acegisecurity.userdetails.User;

public class UserBean {
	private String name;
	private String lastName;
	private String userName;
	private String password;

	// Injected by Spring
	private AuthenticationManager authenticationManager;

	public String login() {
		String retorno = "failureLogin";

		try {
			final String userName = getUserName();
			final String password = getPassword();
			final UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userName, password);

			final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			authReq.setDetails(new WebAuthenticationDetails(request));

			final HttpSession session = request.getSession();
			session.setAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY, userName);

			/*
			 * perform authentication
			 */
			final Authentication auth = getAuthenticationManager().authenticate(authReq);
			
			this.setUserName(((User)auth.getPrincipal()).getUsername());
			this.setPassword(auth.getCredentials().toString());
			this.setName(((User)auth.getPrincipal()).getUsername());
			
			/*
			 * initialize the security context.
			 */
			final SecurityContext secCtx = SecurityContextHolder.getContext();
			secCtx.setAuthentication(auth);
			session.setAttribute(HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY, secCtx);
			
			retorno = "successLogin";
		} catch (BadCredentialsException bce) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário ou senha inválidos"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getCause().toString()));
		}

		return retorno;
	}

	public String logout() {
		final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession(false).removeAttribute(HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY);

		//simulate the SecurityContextLogoutHandler
		SecurityContextHolder.clearContext();
		
		request.getSession(false).invalidate();
		setUserName("");
		setName("");
		setPassword("");
		
		return "logout";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the authenticationManager
	 */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	/**
	 * @param authenticationManager
	 *            the authenticationManager to set
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
