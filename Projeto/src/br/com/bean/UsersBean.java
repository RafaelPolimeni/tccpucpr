package br.com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;
import org.richfaces.component.html.HtmlRichMessage;

import br.com.model.Authority;
import br.com.model.Role;
import br.com.model.User;
import br.com.service.RoleService;
import br.com.service.UsersService;

public class UsersBean extends AbstractBean{
	private UsersService usersServiceImpl;
	private RoleService roleServiceImpl;
	
	private List<Authority> possibleAuthorities;
	private List<Role> possibleRoles;
	
	private List<User> users; 
	
	//User data
	private Integer idUser;
	
	@NotEmpty(message="Deve ser preenchido")
	@Pattern(regex=".*[^\\s].*", message="This string contain only spaces")
	@Length(min=3, max=10, message="Length errado")
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private Date birthDay;
	private String profile;
	private String mainRole;
	private boolean enable;
	private String username;
	private String password;
	private List<Authority> authorities;
	private User user;
	
	private HtmlRichMessage messageFirstName;
	
	public UsersBean() {
		setCreateState(false);
		setUpdateState(false);
		setDetailState(false);
	}
	
	public String findAll(){
		setUsers(usersServiceImpl.findAll());
		return "manageUsers";
	}
	
	public void createUser(){
		if(possibleAuthorities == null)
			setPossibleAuthorities(usersServiceImpl.findAllAuthorities());
		
		if(possibleRoles == null)
			setPossibleRoles(roleServiceImpl.findAll());
		
		setCreateState(true);
		setModalPanelTitle(getLabels().getString("create"));
	}
	
	public void confirmCreate(){
		
	} 
	
	public void showDetails(){
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setPhone(user.getPhone());
		setProfile(user.getAuthority().getDisplayName());
		setMainRole(user.getMainRole().getName());
		setUsername(user.getUserName());
		setPassword(user.getPassword());
		setEnable(user.isEnable());
		setDetailState(true);
		setModalPanelTitle(getLabels().getString("detail"));
	}
	
	public void prepareUpdate(){
		setDetailState(false);
		setUpdateState(true);
		setModalPanelTitle(getLabels().getString("update"));
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
	 * @return the possibleAuthorities
	 */
	public List<SelectItem> getPossibleAuthorities() {
		List<SelectItem> possibleAuthorities = new ArrayList<SelectItem>();
		for(Authority authority : this.possibleAuthorities){
			possibleAuthorities.add(new SelectItem(authority.getIdAuthority(), authority.getDisplayName()));
		}
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
	public List<SelectItem> getPossibleRoles() {
		List<SelectItem> possibleRoles = new ArrayList<SelectItem>();
		for(Role role : this.possibleRoles){
			possibleRoles.add(new SelectItem(role.getIdRole(), role.getName()));
		}
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

	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}

	/**
	 * @return the mainRole
	 */
	public String getMainRole() {
		return mainRole;
	}

	/**
	 * @param mainRole the mainRole to set
	 */
	public void setMainRole(String mainRole) {
		this.mainRole = mainRole;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the messageFirstName
	 */
	public HtmlRichMessage getMessageFirstName() {
		return messageFirstName;
	}

	/**
	 * @param messageFirstName the messageFirstName to set
	 */
	public void setMessageFirstName(HtmlRichMessage messageFirstName) {
		this.messageFirstName = messageFirstName;
	}
}
