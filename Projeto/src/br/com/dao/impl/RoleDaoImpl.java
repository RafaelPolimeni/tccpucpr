package br.com.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.RoleDao;
import br.com.model.Role;

@Repository
public class RoleDaoImpl extends CrudDAOImpl<Role> implements RoleDao {

	@PersistenceContext
	EntityManager entityManager;
	
}