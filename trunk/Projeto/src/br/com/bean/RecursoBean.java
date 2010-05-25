package br.com.bean;

import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.model.Perfil;
import br.com.model.Papel;
import br.com.model.Recurso;

@Service
@Scope(value = "request")
public class RecursoBean extends BeanAbstrato {
	private List<Recurso> recursos;
	private List<SelectItem> autoridades;

	private Integer idRecurso;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String email;
	private String telefone;
	private String usuario;
	private String senha;
	private String confirmarSenha;
	private boolean habilitado;
	private Perfil autoridade;
	private List<Papel> papeis;
	private List<Papel> possiveisPapeis;
	private String locale;

	private Integer idAutoridade;

	private Recurso recurso;

	public void clear() {
		setIdRecurso(null);
		setNome(null);
		setSobrenome(null);
		setDataNascimento(null);
		setEmail(null);
		setTelefone(null);
		setUsuario(null);
		setSenha(null);
		setAutoridade(null);
		setPapeis(null);
		setLocale(null);
		setIdAutoridade(0);
		setConfirmarSenha(null);
		setHabilitado(true);
	}

	/**
	 * @return the recursos
	 */
	public List<Recurso> getRecursos() {
		return recursos;
	}

	/**
	 * @param recursos
	 *            the recursos to set
	 */
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
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
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	/**
	 * @return the papeis
	 */
	public List<Papel> getPapeis() {
		return papeis;
	}

	/**
	 * @param papeis
	 *            the papeis to set
	 */
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the recurso
	 */
	public Recurso getRecurso() {
		return recurso;
	}

	/**
	 * @param recurso
	 *            the recurso to set
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	/**
	 * @return the autoridades
	 */
	public List<SelectItem> getAutoridades() {
		return autoridades;
	}

	/**
	 * @param autoridades
	 *            the autoridades to set
	 */
	public void setAutoridades(List<SelectItem> autoridades) {
		this.autoridades = autoridades;
	}

	/**
	 * @return the idAutoridade
	 */
	public Integer getIdAutoridade() {
		return idAutoridade;
	}

	/**
	 * @param idAutoridade
	 *            the idAutoridade to set
	 */
	public void setIdAutoridade(Integer idAutoridade) {
		this.idAutoridade = idAutoridade;
	}

	/**
	 * @return the confirmarSenha
	 */
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	/**
	 * @param confirmarSenha
	 *            the confirmarSenha to set
	 */
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	/**
	 * @return the possiveisPapeis
	 */
	public List<Papel> getPossiveisPapeis() {
		return possiveisPapeis;
	}

	/**
	 * @param possiveisPapeis
	 *            the possiveisPapeis to set
	 */
	public void setPossiveisPapeis(List<Papel> possiveisPapeis) {
		this.possiveisPapeis = possiveisPapeis;
	}
}