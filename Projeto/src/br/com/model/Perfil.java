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
@Table(name="Perfil")
@NamedQueries( {
		@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p order by p.nome")})
public class Perfil implements EntityInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4097851825814202918L;

	public Perfil() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idAutoridade")
	private Integer idAutoridade;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="nomeVisivel")
	private String nomeVisivel;
	
	public Serializable getPrimaryKey() {
		return getIdAutoridade();
	}

	/**
	 * @return the idAutoridade
	 */
	public Integer getIdAutoridade() {
		return idAutoridade;
	}

	/**
	 * @param idAutoridade the idAutoridade to set
	 */
	public void setIdAutoridade(Integer idAutoridade) {
		this.idAutoridade = idAutoridade;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nomeVisivel
	 */
	public String getNomeVisivel() {
		return nomeVisivel;
	}

	/**
	 * @param nomeVisivel the nomeVisivel to set
	 */
	public void setNomeVisivel(String nomeVisivel) {
		this.nomeVisivel = nomeVisivel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAutoridade == null) ? 0 : idAutoridade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeVisivel == null) ? 0 : nomeVisivel.hashCode());
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
		Perfil other = (Perfil) obj;
		if (idAutoridade == null) {
			if (other.idAutoridade != null)
				return false;
		} else if (!idAutoridade.equals(other.idAutoridade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeVisivel == null) {
			if (other.nomeVisivel != null)
				return false;
		} else if (!nomeVisivel.equals(other.nomeVisivel))
			return false;
		return true;
	}
}