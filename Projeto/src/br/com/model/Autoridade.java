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
@Table(name="Autoridade")
@NamedQueries( {
		@NamedQuery(name = "Autoridade.findAll", query = "SELECT a FROM Autoridade a order by a.nome")})
public class Autoridade implements EntityInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4097851825814202918L;

	public Autoridade() {
		
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
}