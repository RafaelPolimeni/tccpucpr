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

	public Papel() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPapel", nullable = false)
	private Integer idPapel;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "descricao", length = 600, nullable = false)
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
}
