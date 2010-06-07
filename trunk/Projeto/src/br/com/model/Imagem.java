package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Imagem implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8374762569353455791L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idImagem;

	@Column(length = 200, nullable = false)
	private String descricao;

	@Column(length = 200, nullable = false)
	private String caminho;

	@Override
	public Serializable getPrimaryKey() {
		return getIdImagem();
	}

	/**
	 * @return the idImagem
	 */
	public Integer getIdImagem() {
		return idImagem;
	}

	/**
	 * @param idImagem
	 *            the idImagem to set
	 */
	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
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

	/**
	 * @return the caminho
	 */
	public String getCaminho() {
		return caminho;
	}

	/**
	 * @param caminho
	 *            the caminho to set
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}