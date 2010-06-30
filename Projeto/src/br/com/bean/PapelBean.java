package br.com.bean;

import java.util.List;

import org.richfaces.model.selection.SimpleSelection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.model.Papel;

@Service
@Scope(value = "request")
public class PapelBean extends BeanAbstrato {

	private Integer idPapel;
	private String nome;
	private String descricao;
	private List<Papel> papeis;
	private Papel papel;

	private SimpleSelection selecaoPapeis;

	public void clear() {
		setIdPapel(null);
		setNome(null);
		setDescricao(null);
		setPapel(null);
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

	/**
	 * @return the papeis
	 */
	public List<Papel> getPapeis() {
		return papeis;
	}

	/**
	 * @param papeis
	 *            the papeis to set
	 */
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	/**
	 * @return the papel
	 */
	public Papel getPapel() {
		return papel;
	}

	/**
	 * @param papel
	 *            the papel to set
	 */
	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	/**
	 * @return the selecaoPapeis
	 */
	public SimpleSelection getSelecaoPapeis() {
		return selecaoPapeis;
	}

	/**
	 * @param selecaoPapeis
	 *            the selecaoPapeis to set
	 */
	public void setSelecaoPapeis(SimpleSelection selecaoPapeis) {
		this.selecaoPapeis = selecaoPapeis;
	}
}