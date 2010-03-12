package br.com.bean;

import java.util.Date;
import java.util.List;

import br.com.model.Authority;
import br.com.model.Role;
import br.com.model.User;
import br.com.service.RoleService;
import br.com.service.UsersService;

public class UsersBean {
	private UsersService usersServiceImpl;
	private RoleService roleServiceImpl;
	
	private List<Authority> possibleAuthorities;
	private List<Role> possibleRoles;
	
	private List<User> users; 
	
	//User data
	private Integer idUser;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Date birthDay;
	private String username;
	private String password;
	private List<Authority> authorities;
	private User user;
	
	// States
	private boolean createState;
	private boolean updateState;
	private boolean detailState;
	private String titleModal;
	
	public UsersBean() {
		setCreateState(false);
		setUpdateState(false);
		setDetailState(false);
	}
	
	public String findAll(){
		setUsers(usersServiceImpl.findAll());
		return "manageUsers";
	}
	
	public String createUser(){
		setPossibleAuthorities(usersServiceImpl.findAllAuthorities());
		setPossibleRoles(roleServiceImpl.findAll());
		setCreateState(true);
		return "createUser";
	}
	
	public void showDetails(){
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setPhone(user.getPhone());
		setUsername(user.getUserName());
		setPassword(user.getPassword());
		setDetailState(true);
		setTitleModal("Detalhe");
	}
	
	public void prepareUpdate(){
		setDetailState(false);
		setUpdateState(true);
	}
	
	public void confirmUpdate(){
		//update
		setUpdateState(false);
		setUsers(usersServiceImpl.findAll());
	}
	
	public void hideModalPanelForm(){
		setCreateState(false);
		setUpdateState(false);
		setDetailState(false);
	}
	
	/**
	 * @return the usersServiceImpl
	 */
	public UsersService getUsersServiceImpl() {
		return usersServiceImpl;
	}

	/**
	 * @param usersServiceImpl the usersServiceImpl to set
	 */
	public void setUsersServiceImpl(UsersService usersServiceImpl) {
		this.usersServiceImpl = usersServiceImpl;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
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
	 * @return the idUser
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the authorities
	 */
	public List<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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

	/**
	 * @return the possibleAuthorities
	 */
	public List<Authority> getPossibleAuthorities() {
		return possibleAuthorities;
	}

	/**
	 * @param possibleAuthorities the possibleAuthorities to set
	 */
	public void setPossibleAuthorities(List<Authority> possibleAuthorities) {
		this.possibleAuthorities = possibleAuthorities;
	}

	/**
	 * @return the possibleRoles
	 */
	public List<Role> getPossibleRoles() {
		return possibleRoles;
	}

	/**
	 * @param possibleRoles the possibleRoles to set
	 */
	public void setPossibleRoles(List<Role> possibleRoles) {
		this.possibleRoles = possibleRoles;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
