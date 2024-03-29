package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Papel")
@NamedQueries( { @NamedQuery(name = "Papel.findAll", query = "SELECT p FROM Papel p order by p.nome") })
public class Papel implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5316922761586243805L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPapel;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 1000, nullable = false)
	private String descricao;

	@Override
	public Serializable getPrimaryKey() {
		return getIdPapel();
	}

	/**
	 * @return the idPapel
	 */
	public Integer getIdPapel() {
		return idPapel;
	}

	/**
	 * @param idPapel
	 *            the idPapel to set
	 */
	public void setIdPapel(Integer idPapel) {
		this.idPapel = idPapel;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idPapel == null) ? 0 : idPapel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Papel other = (Papel) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idPapel == null) {
			if (other.idPapel != null)
				return false;
		} else if (!idPapel.equals(other.idPapel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}