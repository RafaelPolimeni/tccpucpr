package br.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Recurso")
@NamedQueries( { @NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r order by r.nome"),
	    @NamedQuery(name = "Recurso.habilitados", query = "SELECT r FROM Recurso r where r.habilitado = true order by r.nome"),
		@NamedQuery(name = "Recurso.findByUsername", query = "SELECT r FROM Recurso r WHERE r.usuario = :userName"),
		@NamedQuery(name = "Recurso.observadores", query = "SELECT r FROM Recurso r WHERE r.autoridade.idAutoridade = 4"),
		@NamedQuery(name = "Recurso.gerentes", query = "SELECT r FROM Recurso r WHERE r.autoridade.idAutoridade = 1 or r.autoridade.idAutoridade = 2") })
public class Recurso implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1062671905024286633L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRecurso", nullable = false)
	private Integer idRecurso;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 100, nullable = false)
	private String sobrenome;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 20, nullable = true)
	private String telefone;

	@Column(length = 30, nullable = false)
	private String usuario;

	@Column(length = 20, nullable = false)
	private String senha;

	@Column(nullable = false)
	private boolean habilitado;

	@ManyToOne(fetch = FetchType.EAGER)
	private Perfil autoridade;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Papel> papeis;

	@Column(length = 6, nullable = false)
	private String locale;
	
	@Transient
	private String papelProjeto;

	public Serializable getPrimaryKey() {
		return getIdRecurso();
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
	 * @return the autoridadee
	 */
	public Perfil getAutoridade() {
		return autoridade;
	}

	/**
	 * @param autoridadee
	 *            the autoridadee to set
	 */
	public void setAutoridadee(Perfil autoridade) {
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
	 * @param autoridade
	 *            the autoridade to set
	 */
	public void setAutoridade(Perfil autoridade) {
		this.autoridade = autoridade;
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
	 * @return the papelProjeto
	 */
	public String getPapelProjeto() {
		return papelProjeto;
	}

	/**
	 * @param papelProjeto the papelProjeto to set
	 */
	public void setPapelProjeto(String papelProjeto) {
		this.papelProjeto = papelProjeto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autoridade == null) ? 0 : autoridade.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (habilitado ? 1231 : 1237);
		result = prime * result + ((idRecurso == null) ? 0 : idRecurso.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((papeis == null) ? 0 : papeis.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recurso other = (Recurso) obj;
		if (idRecurso == null) {
			if (other.idRecurso != null)
				return false;
		} else if (!idRecurso.equals(other.idRecurso))
			return false;
		return true;
	}
}