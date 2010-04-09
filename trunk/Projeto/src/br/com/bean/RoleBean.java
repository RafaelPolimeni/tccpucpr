package br.com.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.Role;
import br.com.service.RoleService;

public class RoleBean extends AbstractBean{
	
	private Integer idRole;
	private String name;
	private String description;
	
	private List<Role> roles;
	
	private Role role;
	
	// Service
	@Autowired
	private RoleService roleServiceImpl;
	
	public RoleBean() {
		setListState();
	}

	public String findAll(){
		setRoles(roleServiceImpl.findAll());
		setListState();
		return "manageRoles";
	}
	
	public void createRole(){
		clear();
		setCreateState();
	}
	
	public void confirmCreate(){
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		
		roleServiceImpl.save(role);
		
		setRoles(roleServiceImpl.findAll());
		clear();
		setListState();
	}
	
	public void showDetails(){
		setName(role.getName());
		setDescription(role.getDescription());
		setIdRole(role.getIdRole());
		
		setDetailState();
	}
	
	public void prepareUpdate(){
		setUpdateState();
	}
	
	public void confirmUpdate(){
		role.setName(getName());
		role.setDescription(getDescription());
		
		roleServiceImpl.update(role);
		
		setListState();
		setRoles(roleServiceImpl.findAll());
	}
	
	public void delete(){
		roleServiceImpl.delete(role);
		setListState();
		setRoles(roleServiceImpl.findAll());
	} 
	
	private void clear(){
		setName("");
		setDescription("");
		setRole(null);
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the roleServiceImpl
	 */
	public RoleService getRoleServiceImpl() {
		return roleServiceImpl;
	}

	/**
	 * @param roleServiceImpl the roleServiceImpl to set
	 */
	public void setRoleServiceImpl(RoleService roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
}