package br.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dao.RoleDao;
import br.com.model.Role;
import br.com.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDaoImpl;

	public List<Role> findAll() {
		List<Role> roles = roleDaoImpl.findAll(Role.class);
		return roles;
	}
	
	public void add(Role role){
		roleDaoImpl.save(role);
	}

	/**
	 * @return the roleDaoImpl
	 */
	public RoleDao getRoleDaoImpl() {
		return roleDaoImpl;
	}

	/**
	 * @param roleDaoImpl
	 *            the roleDaoImpl to set
	 */
	public void setRoleDaoImpl(RoleDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}
}
