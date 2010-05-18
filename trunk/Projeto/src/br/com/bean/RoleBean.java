package br.com.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.Role;
import br.com.service.RoleService;

public class RoleBean extends AbstractBean{
	
	private Integer idRole;
	private String name;
	private String description;
	
	private List<Role> roles;
	
	private Role role;
	
	@Autowired
	private RoleService roleServiceImpl;
	
	public RoleBean() {
		setListState();
		ResourceBundle labels = getLabels();
		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.roles.list"));
		
		this.setBreadCrumb(breadCrumb);
	}

	public String findAll(){
		setRoles(roleServiceImpl.findAll());
		setListState();
		setPageMessage(getLabels().getString("listPage"));
		
		return "manageRoles";
	}
	
	public void prepareCreate(){
		clear();
		setCreateState();
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.create"));
		
		setPageMessage(getLabels().getString("createPage"));
	}
	
	public void confirmCreate(){
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		
		roleServiceImpl.save(role);
		
		setRoles(roleServiceImpl.findAll());
		clear();
		setListState();
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.list"));
		
		setPageMessage(getLabels().getString("listPage"));
	}
	
	public void showDetails(){
		setName(role.getName());
		setDescription(role.getDescription());
		setIdRole(role.getIdRole());
		
		setDetailState();
		
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.detail"));
		
		setPageMessage(getLabels().getString("detailPage"));
	}
	
	public void prepareUpdate(){
		setUpdateState();
		
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.update"));
		
		setPageMessage(getLabels().getString("updatePage"));
	}
	
	public void confirmUpdate(){
		role.setName(getName());
		role.setDescription(getDescription());
		
		roleServiceImpl.update(role);
		
		setListState();
		setRoles(roleServiceImpl.findAll());
		
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.list"));
		
		setPageMessage(getLabels().getString("listPage"));
	}
	
	public void delete(){
		roleServiceImpl.delete(role);
		setListState();
		setRoles(roleServiceImpl.findAll());
		
		getBreadCrumb().remove(getBreadCrumb().size() - 1);
		getBreadCrumb().add(getLabels().getString("breadCrumb.roles.list"));
		
		setPageMessage(getLabels().getString("listPage"));
	} 
	
	private void clear(){
		setIdRole(null);
		setName(null);
		setDescription(null);
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