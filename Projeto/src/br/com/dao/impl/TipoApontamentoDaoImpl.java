package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.TipoApontamentoDao;
import br.com.model.TipoApontamento;

@Repository
public class TipoApontamentoDaoImpl extends CrudDAOImpl<TipoApontamento> implements TipoApontamentoDao {

	@PersistenceContext
	EntityManager entityManager;

}