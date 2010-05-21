package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Recurso")
@NamedQueries( {
		@NamedQuery(name = "Recurso.findAll", query = "SELECT r FROM Recurso r order by r.nome"),
		@NamedQuery(name = "Recurso.findByUsername", query = "SELECT r FROM Recurso r WHERE r.usuario = :userName")})
public class Recurso implements EntityInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6663378091231662507L;

	public Recurso() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRecurso", nullable = false)
	private Integer idRecurso;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "dataNascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name = "usuario", nullable = false)
	private String usuario;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "habilitado", nullable = false)
	private boolean habilitado;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Autoridade autoridade;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Papel papelPrincipal;
	
	@Column
	private String locale;
	
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
	 * @param idRecurso the idRecurso to set
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
	 * @param nome the nome to set
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
	 * @param sobrenome the sobrenome to set
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
	 * @param dataNascimento the dataNascimento to set
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
	 * @param email the email to set
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
	 * @param telefone the telefone to set
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
	 * @param usuario the usuario to set
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
	 * @param senha the senha to set
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
	 * @param habilitado the habilitado to set
	 */
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * @return the autoridadee
	 */
	public Autoridade getAutoridade() {
		return autoridade;
	}

	/**
	 * @param autoridadee the autoridadee to set
	 */
	public void setAutoridadee(Autoridade autoridade) {
		this.autoridade = autoridade;
	}

	/**
	 * @return the papelPrincipal
	 */
	public Papel getPapelPrincipal() {
		return papelPrincipal;
	}

	/**
	 * @param papelPrincipal the papelPrincipal to set
	 */
	public void setPapelPrincipal(Papel papelPrincipal) {
		this.papelPrincipal = papelPrincipal;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
}