package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.UserDao;
import br.com.model.User;

@Repository
public class UserDaoImpl extends CrudDAOImpl<User> implements UserDao {

	@PersistenceContext
	EntityManager entityManager;

	public User findUserByName(String userName) {
		try{
			return (User) entityManager.createNamedQuery("User.findByUsername").setParameter("userName", userName).getSingleResult();
		}catch (NoResultException nre) {
			return null;
		}
	}
}