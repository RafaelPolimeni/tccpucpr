package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoApontamento implements EntityInterface {

	private static final long serialVersionUID = 792498007677468760L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTipoApontamento;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 1000, nullable = false)
	private String descricao;

	@Override
	public Serializable getPrimaryKey() {
		return getIdTipoApontamento();
	}

	/**
	 * @return the idTipoApontamento
	 */
	public Integer getIdTipoApontamento() {
		return idTipoApontamento;
	}

	/**
	 * @param idTipoApontamento
	 *            the idTipoApontamento to set
	 */
	public void setIdTipoApontamento(Integer idTipoApontamento) {
		this.idTipoApontamento = idTipoApontamento;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idTipoApontamento == null) ? 0 : idTipoApontamento.hashCode());
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
		TipoApontamento other = (TipoApontamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idTipoApontamento == null) {
			if (other.idTipoApontamento != null)
				return false;
		} else if (!idTipoApontamento.equals(other.idTipoApontamento))
			return false;
		return true;
	}
}