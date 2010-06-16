package br.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Projeto")
@NamedQueries( { @NamedQuery(name = "Projeto.findAll", query = "SELECT p FROM Projeto p order by p.nome") })
public class Projeto implements EntityInterface {

	private static final long serialVersionUID = 5405661644221470932L;
	
	@Transient
	public static final int PROJETO_CRIADO = 1;
	@Transient
	public static final int PROJETO_INICIADO = 2;
	@Transient
	public static final int PROJETO_FINALIZADO = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProjeto;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 1000, nullable = false)
	private String descricao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataInicioPrevista;
	
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dataFimPrevista;

	@Column(length = 1, nullable = false)
	private Integer status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(columnDefinition = "idCriador")
	private Recurso criador;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recursoProjetoPK.projeto", cascade = CascadeType.ALL)
	private List<RecursoProjeto> recursos;

	@ManyToMany(targetEntity = Recurso.class, cascade = CascadeType.MERGE)
	@JoinTable(name = "observadoresProjeto", 
			joinColumns = @JoinColumn(nullable = false, referencedColumnName = "idProjeto"), 
			inverseJoinColumns = @JoinColumn(nullable = false, referencedColumnName = "idRecurso"))
	private List<Recurso> observadores;

	@ManyToMany(targetEntity = Recurso.class, cascade = CascadeType.MERGE)
	@JoinTable(name = "gerentesProjeto", 
			joinColumns = @JoinColumn(nullable = false, referencedColumnName = "idProjeto"), 
			inverseJoinColumns = @JoinColumn(nullable = false, referencedColumnName = "idRecurso"))
	private List<Recurso> gerentes;

	@OneToMany(cascade = CascadeType.MERGE)
	private List<Comentario> comentarios;

	@OneToMany(cascade = CascadeType.MERGE)
	private List<Imagem> imagens;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HistoricoProjeto> historicos;

	@Override
	public Serializable getPrimaryKey() {
		return getIdProjeto();
	}

	/**
	 * @return the idProjeto
	 */
	public Integer getIdProjeto() {
		return idProjeto;
	}

	/**
	 * @param idProjeto
	 *            the idProjeto to set
	 */
	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the dataCriacao
	 */
	public Date getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao
	 *            the dataCriacao to set
	 */
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio
	 *            the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	
	/**
	 * @return the dataInicioPrevista
	 */
	public Date getDataInicioPrevista() {
		return dataInicioPrevista;
	}

	/**
	 * @param dataInicioPrevista the dataInicioPrevista to set
	 */
	public void setDataInicioPrevista(Date dataInicioPrevista) {
		this.dataInicioPrevista = dataInicioPrevista;
	}

	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim
	 *            the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return the dataFimPrevista
	 */
	public Date getDataFimPrevista() {
		return dataFimPrevista;
	}

	/**
	 * @param dataFimPrevista
	 *            the dataFimPrevista to set
	 */
	public void setDataFimPrevista(Date dataFimPrevista) {
		this.dataFimPrevista = dataFimPrevista;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the criador
	 */
	public Recurso getCriador() {
		return criador;
	}

	/**
	 * @param criador
	 *            the criador to set
	 */
	public void setCriador(Recurso criador) {
		this.criador = criador;
	}

	/**
	 * @return the recursos
	 */
	public List<RecursoProjeto> getRecursos() {
		return recursos;
	}

	/**
	 * @param recursos
	 *            the recursos to set
	 */
	public void setRecursos(List<RecursoProjeto> recursos) {
		this.recursos = recursos;
	}

	/**
	 * @return the observadores
	 */
	public List<Recurso> getObservadores() {
		return observadores;
	}

	/**
	 * @param observadores
	 *            the observadores to set
	 */
	public void setObservadores(List<Recurso> observadores) {
		this.observadores = observadores;
	}

	/**
	 * @return the gerentes
	 */
	public List<Recurso> getGerentes() {
		return gerentes;
	}

	/**
	 * @param gerentes
	 *            the gerentes to set
	 */
	public void setGerentes(List<Recurso> gerentes) {
		this.gerentes = gerentes;
	}

	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios
	 *            the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	/**
	 * @return the imagens
	 */
	public List<Imagem> getImagens() {
		return imagens;
	}

	/**
	 * @param imagens
	 *            the imagens to set
	 */
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	/**
	 * @return the historicos
	 */
	public List<HistoricoProjeto> getHistoricos() {
		return historicos;
	}

	/**
	 * @param historicos
	 *            the historicos to set
	 */
	public void setHistoricos(List<HistoricoProjeto> historicos) {
		this.historicos = historicos;
	}
}