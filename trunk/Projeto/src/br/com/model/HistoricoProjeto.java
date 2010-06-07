package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistoricoProjeto implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 500274337328601621L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idHistoricoProjeto;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idRecurso")
	private Recurso recurso;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idProjeto")
	private Projeto projeto;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false)
	private boolean adicionou;

	@Override
	public Serializable getPrimaryKey() {
		return getIdHistoricoProjeto();
	}

	/**
	 * @return the idHistoricoAtividade
	 */
	public Integer getIdHistoricoProjeto() {
		return idHistoricoProjeto;
	}

	/**
	 * @param idHistoricoAtividade
	 *            the idHistoricoAtividade to set
	 */
	public void setIdHistoricoProjeto(Integer idHistoricoProjeto) {
		this.idHistoricoProjeto = idHistoricoProjeto;
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
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the adicionou
	 */
	public boolean isAdicionou() {
		return adicionou;
	}

	/**
	 * @param adicionou
	 *            the adicionou to set
	 */
	public void setAdicionou(boolean adicionou) {
		this.adicionou = adicionou;
	}
}
