package br.com.bean;

import java.util.List;

import org.richfaces.model.selection.SimpleSelection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.model.TipoApontamento;

@Service
@Scope(value = "request")
public class TipoApontamentoBean extends BeanAbstrato {

	private Integer idTipoApontamento;
	private String nome;
	private String descricao;

	private List<TipoApontamento> tiposApontamentos;
	private TipoApontamento tipoApontamento;

	private SimpleSelection selecaoTipoApontamento;

	public void clear() {
		setIdTipoApontamento(null);
		setNome(null);
		setDescricao(null);
		setTipoApontamento(null);
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

	/**
	 * @return the tiposApontamentos
	 */
	public List<TipoApontamento> getTiposApontamentos() {
		return tiposApontamentos;
	}

	/**
	 * @param tiposApontamentos
	 *            the tiposApontamentos to set
	 */
	public void setTiposApontamentos(List<TipoApontamento> tiposApontamentos) {
		this.tiposApontamentos = tiposApontamentos;
	}

	/**
	 * @return the tipoApontamento
	 */
	public TipoApontamento getTipoApontamento() {
		return tipoApontamento;
	}

	/**
	 * @param tipoApontamento
	 *            the tipoApontamento to set
	 */
	public void setTipoApontamento(TipoApontamento tipoApontamento) {
		this.tipoApontamento = tipoApontamento;
	}

	/**
	 * @return the selecaoTipoApontamento
	 */
	public SimpleSelection getSelecaoTipoApontamento() {
		return selecaoTipoApontamento;
	}

	/**
	 * @param selecaoTipoApontamento
	 *            the selecaoTipoApontamento to set
	 */
	public void setSelecaoTipoApontamento(SimpleSelection selecaoTipoApontamento) {
		this.selecaoTipoApontamento = selecaoTipoApontamento;
	}
}