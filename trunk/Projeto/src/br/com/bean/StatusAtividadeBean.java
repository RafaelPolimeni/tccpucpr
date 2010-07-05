package br.com.bean;

import java.util.List;

import org.richfaces.model.selection.SimpleSelection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.model.StatusAtividade;

@Service
@Scope(value = "request")
public class StatusAtividadeBean extends BeanAbstrato {

	private Integer idStatusAtividade;
	private String nome;
	private String descricao;
	private List<StatusAtividade> status;
	private StatusAtividade statusAtividade;

	private SimpleSelection selecaoStatus;

	public void clear() {
		setIdStatusAtividade(null);
		setNome(null);
		setDescricao(null);
		setStatusAtividade(null);
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

	/**
	 * @return the status
	 */
	public List<StatusAtividade> getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(List<StatusAtividade> status) {
		this.status = status;
	}

	/**
	 * @return the statusAtividade
	 */
	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}

	/**
	 * @param statusAtividade
	 *            the statusAtividade to set
	 */
	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

	/**
	 * @return the selecaoStatus
	 */
	public SimpleSelection getSelecaoStatus() {
		return selecaoStatus;
	}

	/**
	 * @param selecaoStatus
	 *            the selecaoStatus to set
	 */
	public void setSelecaoStatus(SimpleSelection selecaoStatus) {
		this.selecaoStatus = selecaoStatus;
	}
}