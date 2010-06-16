package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.ProjetoDao;
import br.com.model.Projeto;

@Repository
public class ProjetoDaoImpl extends CrudDAOImpl<Projeto> implements ProjetoDao {

	@PersistenceContext
	EntityManager entityManager;

}