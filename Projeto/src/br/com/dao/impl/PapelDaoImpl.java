package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.PapelDao;
import br.com.model.Papel;

@Repository
public class PapelDaoImpl extends CrudDAOImpl<Papel> implements PapelDao {

	@PersistenceContext
	EntityManager entityManager;

}