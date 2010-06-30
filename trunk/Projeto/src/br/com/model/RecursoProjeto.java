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
	 * @param recursoProjetoPK
	 *            the recursoProjetoPK to set
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
	 * @param papel
	 *            the papel to set
	 */
	public void setPapel(Papel papel) {
		this.papel = papel;
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
		result = prime * result + ((papel == null) ? 0 : papel.hashCode());
		result = prime * result + ((recursoProjetoPK == null) ? 0 : recursoProjetoPK.hashCode());
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
		RecursoProjeto other = (RecursoProjeto) obj;

		if (recursoProjetoPK == null) {
			if (other.recursoProjetoPK != null)
				return false;
		} else if (!recursoProjetoPK.getRecurso().getIdRecurso().equals(other.recursoProjetoPK.getRecurso().getIdRecurso()))
			return false;
		return true;
	}
}