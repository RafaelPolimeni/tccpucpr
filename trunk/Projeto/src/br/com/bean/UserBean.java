package br.com.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Service;

import br.com.model.Perfil;
import br.com.service.UserService;

@Service
@Scope(value = "session")
public class UserBean extends BeanAbstrato {
	private Integer idRecurso;
	private String nome;
	private String sobrenome;
	private String usuario;
	private String senha;
	private boolean habilitado;
	private Perfil autoridade;

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
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);
		}

		FacesContext.getCurrentInstance().responseComplete();
		// It's OK to return null here because Faces is just going to exit.

		return null;
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
	 * @return the idRecurso
	 */
	public Integer getIdRecurso() {
		return idRecurso;
	}

	/**
	 * @param idRecurso
	 *            the idRecurso to set
	 */
	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return sobrenome;
	}

	/**
	 * @param sobrenome
	 *            the sobrenome to set
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the habilitado
	 */
	public boolean isHabilitado() {
		return habilitado;
	}

	/**
	 * @param habilitado
	 *            the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * @return the autoridade
	 */
	public Perfil getAutoridade() {
		return autoridade;
	}

	/**
	 * @param autoridade
	 *            the autoridade to set
	 */
	public void setAutoridade(Perfil autoridade) {
		this.autoridade = autoridade;
	}
}