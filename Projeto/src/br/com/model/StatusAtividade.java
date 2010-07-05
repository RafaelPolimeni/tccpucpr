package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( { @NamedQuery(name = "StatusAtividade.findAll", query = "SELECT s FROM StatusAtividade s order by s.nome") })
public class StatusAtividade implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2647558483887057545L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idStatusAtividade;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 1000, nullable = false)
	private String descricao;

	@Override
	public Serializable getPrimaryKey() {
		return getIdStatusAtividade();
	}

	/**
	 * @return the idStatusAtividade
	 */
	public Integer getIdStatusAtividade() {
		return idStatusAtividade;
	}

	/**
	 * @param idStatusAtividade
	 *            the idStatusAtividade to set
	 */
	public void setIdStatusAtividade(Integer idStatusAtividade) {
		this.idStatusAtividade = idStatusAtividade;
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
		result = prime * result + ((idStatusAtividade == null) ? 0 : idStatusAtividade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		StatusAtividade other = (StatusAtividade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idStatusAtividade == null) {
			if (other.idStatusAtividade != null)
				return false;
		} else if (!idStatusAtividade.equals(other.idStatusAtividade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}