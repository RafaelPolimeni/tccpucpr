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
public class HistoricoAtividade implements EntityInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 500274337328601621L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idHistoricoAtividade;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idRecurso")
	private Recurso recurso;

	@ManyToOne(optional = false)
	@JoinColumn(name = "idAtividade")
	private Atividade atividade;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false)
	private boolean adicionou;

	@Override
	public Serializable getPrimaryKey() {
		return getIdHistoricoAtividade();
	}
	
	/**
	 * @return the idHistoricoAtividade
	 */
	public Integer getIdHistoricoAtividade() {
		return idHistoricoAtividade;
	}

	/**
	 * @param idHistoricoAtividade
	 *            the idHistoricoAtividade to set
	 */
	public void setIdHistoricoAtividade(Integer idHistoricoAtividade) {
		this.idHistoricoAtividade = idHistoricoAtividade;
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
	 * @return the atividade
	 */
	public Atividade getAtividade() {
		return atividade;
	}

	/**
	 * @param atividade
	 *            the atividade to set
	 */
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
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
