package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.richfaces.model.UploadItem;

@Entity
public class Arquivo implements EntityInterface {

	@Transient
	public static final int IMG = 1;
	@Transient
	public static final int DOC = 2;
	@Transient
	public static final int PDF = 3;
	@Transient
	public static final int OUTROS = 4;

	private static final long serialVersionUID = 8374762569353455791L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idArquivo;

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 300, nullable = false)
	private String descricao;

	@Column(length = 100, nullable = false)
	private String nomeArquivo;

	@Column(length = 2, nullable = false)
	private Integer tipo;

	@Column(length = 300, nullable = false)
	private String caminho;

	@Transient
	private UploadItem arquivo;

	@Override
	public Serializable getPrimaryKey() {
		return getIdArquivo();
	}

	/**
	 * @return the idArquivo
	 */
	public Integer getIdArquivo() {
		return idArquivo;
	}

	/**
	 * @param idArquivo
	 *            the idArquivo to set
	 */
	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
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

	/**
	 * @return the arquivo
	 */
	public UploadItem getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo
	 *            the arquivo to set
	 */
	public void setArquivo(UploadItem arquivo) {
		this.arquivo = arquivo;
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
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * @param nomeArquivo
	 *            the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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
		result = prime * result + ((caminho == null) ? 0 : caminho.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idArquivo == null) ? 0 : idArquivo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Arquivo other = (Arquivo) obj;
		
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}