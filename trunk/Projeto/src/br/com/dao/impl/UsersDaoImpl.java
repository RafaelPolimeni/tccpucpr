package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.UsersDao;
import br.com.model.User;

@Repository
public class UsersDaoImpl extends CrudDAOImpl<User> implements UsersDao {

	@PersistenceContext
	EntityManager entityManager;
	
}