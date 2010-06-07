package br.com.dao;

import java.util.List;

import br.com.model.HistoricoProjeto;
import br.com.model.Projeto;

public interface ProjetoDao extends CrudDAO<Projeto> {

	public void incluirProjeto(Projeto projeto, List<HistoricoProjeto> listaHistorico);
}
