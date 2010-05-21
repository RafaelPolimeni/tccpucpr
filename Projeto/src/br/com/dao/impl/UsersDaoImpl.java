package br.com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.UsersDao;
import br.com.model.Autoridade;
import br.com.model.Recurso;

@Repository
public class UsersDaoImpl extends CrudDaoImpl<Recurso> implements UsersDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Autoridade> findAllAuthorities(){
		return entityManager.createNamedQuery(Autoridade.class.getSimpleName() + ".findAll").getResultList();
	}
	
}