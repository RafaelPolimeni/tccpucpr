package br.com.dao.impl;

import java.util.List;

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
		List users = entityManager.createNamedQuery("Recurso.findByUsername").setParameter("userName", userName).getResultList();

		if (users.size() == 0) {
			return null;
		}

		return ((Recurso) users.get(0));
	}
}