package br.com.bean;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.model.Projeto;
import br.com.model.Recurso;

@Service
@Scope(value = "request")
public class ProjetoBean extends BeanAbstrato {
	private List<Projeto> projetos;

	private Integer idProjeto;
	private String nome;
	private String descricao;
	private Date dataCriacao;
	private Date dataInicio;
	private Date dataFim;
	private Date dataFimPrevista;
	private Integer status;
	private Recurso criador;
	private List<Recurso> observadores;
	private List<Recurso> possiveisObservadores;
	private List<Recurso> gerentes;
	private List<Recurso> possiveisGerentes;
	private List<Recurso> participantes;
	private List<Recurso> possiveisParticipantes;

	private Projeto projeto;
	private Recurso recursoTemp;
	
	private boolean showModalObservadores;

	public void clear() {
		setIdProjeto(null);
		setNome(null);
		setDescricao(null);
		setDataCriacao(null);
		setDataInicio(null);
		setDataFim(null);
		setDataFimPrevista(null);
		setStatus(0);
		setCriador(null);
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
	 * @return the projetos
	 */
	public List<Projeto> getProjetos() {
		return projetos;
	}

	/**
	 * @param projetos
	 *            the projetos to set
	 */
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	/**
	 * @return the possiveisObservadores
	 */
	public List<Recurso> getPossiveisObservadores() {
		return possiveisObservadores;
	}

	/**
	 * @param possiveisObservadores
	 *            the possiveisObservadores to set
	 */
	public void setPossiveisObservadores(List<Recurso> possiveisObservadores) {
		this.possiveisObservadores = possiveisObservadores;
	}

	/**
	 * @return the possiveisGerentes
	 */
	public List<Recurso> getPossiveisGerentes() {
		return possiveisGerentes;
	}

	/**
	 * @param possiveisGerentes
	 *            the possiveisGerentes to set
	 */
	public void setPossiveisGerentes(List<Recurso> possiveisGerentes) {
		this.possiveisGerentes = possiveisGerentes;
	}

	/**
	 * @return the participantes
	 */
	public List<Recurso> getParticipantes() {
		return participantes;
	}

	/**
	 * @param participantes
	 *            the participantes to set
	 */
	public void setParticipantes(List<Recurso> participantes) {
		this.participantes = participantes;
	}

	/**
	 * @return the possiveisParticipantes
	 */
	public List<Recurso> getPossiveisParticipantes() {
		return possiveisParticipantes;
	}

	/**
	 * @param possiveisParticipantes
	 *            the possiveisParticipantes to set
	 */
	public void setPossiveisParticipantes(List<Recurso> possiveisParticipantes) {
		this.possiveisParticipantes = possiveisParticipantes;
	}

	/**
	 * @return the recursoTemp
	 */
	public Recurso getRecursoTemp() {
		return recursoTemp;
	}

	/**
	 * @param recursoTemp
	 *            the recursoTemp to set
	 */
	public void setRecursoTemp(Recurso recursoTemp) {
		this.recursoTemp = recursoTemp;
	}

	/**
	 * @return the showModalObservadores
	 */
	public boolean isShowModalObservadores() {
		return showModalObservadores;
	}

	/**
	 * @param showModalObservadores the showModalObservadores to set
	 */
	public void setShowModalObservadores(boolean showModalObservadores) {
		this.showModalObservadores = showModalObservadores;
	}
}