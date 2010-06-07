package br.com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.ProjetoDao;
import br.com.model.HistoricoProjeto;
import br.com.model.Projeto;

@Repository
public class ProjetoDaoImpl extends CrudDAOImpl<Projeto> implements ProjetoDao {

	@PersistenceContext
	EntityManager entityManager;

	public void incluirProjeto(Projeto projeto, List<HistoricoProjeto> listaHistorico) {
		entityManager.persist(projeto);

		for (HistoricoProjeto historico : listaHistorico) {
			entityManager.persist(historico);
		}
	}
}