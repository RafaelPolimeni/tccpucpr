package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.dao.UserDao;
import br.com.model.User;

public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager entityManager;

	public User findUser(String userName) {
		Query query = entityManager.createQuery("select user from User user where user.userName = :userName");
		query.setParameter("userName", userName);
		User user = (User) query.getSingleResult();

		return user;
	}
}