package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.UserDao;
import br.com.model.Recurso;

@Repository
public class UserDaoImpl extends CrudDAOImpl<Recurso> implements UserDao {

	@PersistenceContext
	EntityManager entityManager;

	public Recurso findUserByName(String userName) {
		return (Recurso) entityManager.createNamedQuery("Recurso.findByUsername").setParameter("userName", userName).getSingleResult();
	}
}