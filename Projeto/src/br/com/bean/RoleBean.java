package br.com.bean;

import java.util.List;

import br.com.model.Role;
import br.com.service.RoleService;

public class RoleBean {
	
	private Integer idRole;
	private String name;
	private String description;
	
	private List<Role> roles;
	
	private Role role;
	
	// States
	private boolean createState;
	private boolean updateState;
	private boolean detailState;
	private String titleModal;
	
	// Service
	private RoleService roleServiceImpl;
	
	public RoleBean() {
		setCreateState(false);
		setUpdateState(false);
		setDetailState(false);
	}

	public String findAll(){
		setRoles(roleServiceImpl.findAll());
		return "roles";
	}
	
	public void createRole(){
		clear();
		setCreateState(true);
		setTitleModal("Inclusão");
	}
	
	public void confirmCreate(){
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		
		roleServiceImpl.add(role);
		
		setRoles(roleServiceImpl.findAll());
		clear();
		setCreateState(false);	
	}
	
	public void showDetails(){
		setName(role.getName());
		setDescription(role.getDescription());
		setIdRole(role.getIdRole());
		
		setDetailState(true);
		setTitleModal("Detalhe");
	}
	
	public void hideModalPanelForm(){
		setCreateState(false);
		setUpdateState(false);
		setDetailState(false);
	}
	
	public void updateRole(){
		setCreateState(false);
		setUpdateState(true);
		setTitleModal("Atualização");
	}
	
	public void prepareUpdate(){
		setUpdateState(true);
		setCreateState(false);
		setDetailState(false);
	}
	
	public void confirmUpdate(){
		//update
		setUpdateState(false);
		setCreateState(false);
		setDetailState(false);
		setRoles(roleServiceImpl.findAll());
	}
	
	public void delete(){
		//update
		setUpdateState(false);
		setCreateState(false);
		setDetailState(false);
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
	 * @return the createState
	 */
	public boolean isCreateState() {
		return createState;
	}

	/**
	 * @param createState the createState to set
	 */
	public void setCreateState(boolean createState) {
		this.createState = createState;
	}

	/**
	 * @return the updateState
	 */
	public boolean isUpdateState() {
		return updateState;
	}

	/**
	 * @param updateState the updateState to set
	 */
	public void setUpdateState(boolean updateState) {
		this.updateState = updateState;
	}

	/**
	 * @return the detailState
	 */
	public boolean isDetailState() {
		return detailState;
	}

	/**
	 * @param detailState the detailState to set
	 */
	public void setDetailState(boolean detailState) {
		this.detailState = detailState;
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

	/**
	 * @return the titleModal
	 */
	public String getTitleModal() {
		return titleModal;
	}

	/**
	 * @param titleModal the titleModal to set
	 */
	public void setTitleModal(String titleModal) {
		this.titleModal = titleModal;
	}
}