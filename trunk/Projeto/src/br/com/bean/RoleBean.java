package br.com.bean;

import java.util.List;

import org.richfaces.component.UIPanel;

import br.com.model.Role;
import br.com.service.RoleService;

public class RoleBean {
	
	private Integer idRole;
	private String name;
	private String description;
	
	private List<Role> roles;
	
	// States
	private boolean listState;
	private boolean createUpdateState;
	
	// Service
	private RoleService roleServiceImpl;
	
	// Panels
	private UIPanel listBox;
	private UIPanel createUpdateBox;
	
	public RoleBean() {
		setListState(true);
		setCreateUpdateState(false);
	}

	public String findAll(){
		setRoles(roleServiceImpl.findAll());
		return "roles";
	}
	
	public void back(){
		System.out.println("VOLTAR");
		this.setListState(true);
		this.setCreateUpdateState(false);
		
		this.clear();
		
		setRoles(roleServiceImpl.findAll());
	}
	
	public void createRole(){
		setListState(false);
		setCreateUpdateState(true);
	}
	
	public void confirmCreate(){
		System.out.println("ENTROU");
		setListState(true);
		setCreateUpdateState(false);
		
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);
		
		roleServiceImpl.add(role);
		
		setRoles(roleServiceImpl.findAll());
	}
	
	private void clear(){
		listBox.getChildren().clear();
		createUpdateBox.getChildren().clear();
		this.setName("");
		this.setDescription("");
		this.setIdRole(null);
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
	 * @return the listState
	 */
	public boolean isListState() {
		return listState;
	}

	/**
	 * @param listState the listState to set
	 */
	public void setListState(boolean listState) {
		this.listState = listState;
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
	 * @return the createUpdateState
	 */
	public boolean isCreateUpdateState() {
		return createUpdateState;
	}

	/**
	 * @param createUpdateState the createUpdateState to set
	 */
	public void setCreateUpdateState(boolean createUpdateState) {
		this.createUpdateState = createUpdateState;
	}

	/**
	 * @return the listBox
	 */
	public UIPanel getListBox() {
		return listBox;
	}

	/**
	 * @param listBox the listBox to set
	 */
	public void setListBox(UIPanel listBox) {
		this.listBox = listBox;
	}

	/**
	 * @return the createUpdateBox
	 */
	public UIPanel getCreateUpdateBox() {
		return createUpdateBox;
	}

	/**
	 * @param createUpdateBox the createUpdateBox to set
	 */
	public void setCreateUpdateBox(UIPanel createUpdateBox) {
		this.createUpdateBox = createUpdateBox;
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
}