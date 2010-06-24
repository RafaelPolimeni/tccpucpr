package br.com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comentario implements EntityInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5365050394521099102L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idComentario;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "idRecurso", nullable = false)
	private Recurso recurso;

	@Column(length = 2000, nullable = false)
	private String comentario;

	@OneToMany
	private List<Arquivo> arquivos;

	@Override
	public Serializable getPrimaryKey() {
		return getIdComentario();
	}

	/**
	 * @return the idComentario
	 */
	public Integer getIdComentario() {
		return idComentario;
	}

	/**
	 * @param idComentario
	 *            the idComentario to set
	 */
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the recurso
	 */
	public Recurso getRecurso() {
		return recurso;
	}

	/**
	 * @param recurso
	 *            the recurso to set
	 */
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the arquivos
	 */
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	/**
	 * @param arquivos
	 *            the arquivos to set
	 */
	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
}