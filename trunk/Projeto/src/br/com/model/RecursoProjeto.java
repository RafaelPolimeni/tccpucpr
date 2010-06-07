package br.com.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RecursoProjeto implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1885374590299201408L;

	@EmbeddedId
	private RecursoProjetoPK recursoProjetoPK;

	@ManyToOne
	private Papel papel;

	@Override
	public Serializable getPrimaryKey() {
		return getRecursoProjetoPK();
	}

	/**
	 * @return the recursoProjetoPK
	 */
	public RecursoProjetoPK getRecursoProjetoPK() {
		return recursoProjetoPK;
	}

	/**
	 * @param recursoProjetoPK the recursoProjetoPK to set
	 */
	public void setRecursoProjetoPK(RecursoProjetoPK recursoProjetoPK) {
		this.recursoProjetoPK = recursoProjetoPK;
	}

	/**
	 * @return the papel
	 */
	public Papel getPapel() {
		return papel;
	}

	/**
	 * @param papel the papel to set
	 */
	public void setPapel(Papel papel) {
		this.papel = papel;
	}
}