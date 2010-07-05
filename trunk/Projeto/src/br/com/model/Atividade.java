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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Atividade")
public class Atividade implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2326186932819387743L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAtividade;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 2000, nullable = false)
	private String descricao;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFim;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFimPrevista;

	@Column(length = 1, nullable = false)
	private Integer tipoAtividade;

	@ManyToOne
	private StatusAtividade status;

	@Column(length = 1, nullable = false)
	private Integer prioridade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCriador")
	private Recurso criador;

	@ManyToOne
	private Projeto projeto;

	@OneToMany
	private List<Atividade> filhas;

	@ManyToMany
	private List<Recurso> recursos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "apontamentoPK.atividade")
	private List<Apontamento> apontamentos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atividade")
	private List<HistoricoAtividade> historicos;

	@OneToMany
	private List<Comentario> comentarios;

	@OneToMany
	private List<Arquivo> arquivos;

	@Override
	public Serializable getPrimaryKey() {
		return getIdAtividade();
	}

	/**
	 * @return the idAtividade
	 */
	public Integer getIdAtividade() {
		return idAtividade;
	}

	/**
	 * @param idAtividade
	 *            the idAtividade to set
	 */
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
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
	 * @param dataFimPrevisto
	 *            the dataFimPrevista to set
	 */
	public void setDataFimPrevista(Date dataFimPrevista) {
		this.dataFimPrevista = dataFimPrevista;
	}

	/**
	 * @return the tipoAtividade
	 */
	public Integer getTipoAtividade() {
		return tipoAtividade;
	}

	/**
	 * @param tipoAtividade
	 *            the tipoAtividade to set
	 */
	public void setTipoAtividade(Integer tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	/**
	 * @return the status
	 */
	public StatusAtividade getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusAtividade status) {
		this.status = status;
	}

	/**
	 * @return the prioridade
	 */
	public Integer getPrioridade() {
		return prioridade;
	}

	/**
	 * @param prioridade
	 *            the prioridade to set
	 */
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
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
	 * @return the apontamentos
	 */
	public List<Apontamento> getApontamentos() {
		return apontamentos;
	}

	/**
	 * @param apontamentos
	 *            the apontamentos to set
	 */
	public void setApontamentos(List<Apontamento> apontamentos) {
		this.apontamentos = apontamentos;
	}

	/**
	 * @return the historicos
	 */
	public List<HistoricoAtividade> getHistoricos() {
		return historicos;
	}

	/**
	 * @param historicos
	 *            the historicos to set
	 */
	public void setHistoricos(List<HistoricoAtividade> historicos) {
		this.historicos = historicos;
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
	 * @return the arquivos
	 */
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	/**
	 * @param arquivos
	 *            the arquivos to set
	 */
	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	/**
	 * @return the filhas
	 */
	public List<Atividade> getFilhas() {
		return filhas;
	}

	/**
	 * @param filhas
	 *            the filhas to set
	 */
	public void setFilhas(List<Atividade> filhas) {
		this.filhas = filhas;
	}

	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto
	 *            the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
}