package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "apontamento")
public class Apontamento implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1885374590299201408L;

	@EmbeddedId
	private ApontamentoPK apontamentoPK;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(length = 1, nullable = false)
	private Integer tipo;

	@ManyToOne
	private TipoApontamento tipoApontamento;

	@Override
	public Serializable getPrimaryKey() {
		return getApontamentoPK();
	}

	/**
	 * @return the apontamentoPK
	 */
	public ApontamentoPK getApontamentoPK() {
		return apontamentoPK;
	}

	/**
	 * @param apontamentoPK
	 *            the apontamentoPK to set
	 */
	public void setApontamentoPK(ApontamentoPK apontamentoPK) {
		this.apontamentoPK = apontamentoPK;
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
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipoApontamento
	 */
	public TipoApontamento getTipoApontamento() {
		return tipoApontamento;
	}

	/**
	 * @param tipoApontamento the tipoApontamento to set
	 */
	public void setTipoApontamento(TipoApontamento tipoApontamento) {
		this.tipoApontamento = tipoApontamento;
	}
}