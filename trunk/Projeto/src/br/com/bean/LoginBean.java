package br.com.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.acegisecurity.ui.AbstractProcessingFilter;

public class LoginBean {

	private String userName;
	private String password;

	public LoginBean() {
		Exception ex = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
				AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);

		if (ex != null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));

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
}
