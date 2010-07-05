package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.StatusAtividadeDao;
import br.com.model.StatusAtividade;

@Repository
public class StatusAtividadeDaoImpl extends CrudDAOImpl<StatusAtividade> implements StatusAtividadeDao {

	@PersistenceContext
	EntityManager entityManager;

}