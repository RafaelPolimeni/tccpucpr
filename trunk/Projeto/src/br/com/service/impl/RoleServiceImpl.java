package br.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.bean.RoleBean;
import br.com.dao.RoleDao;
import br.com.model.Role;
import br.com.service.RoleService;

@Service
@Scope(value="request")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDaoImpl;
	@Autowired
	private RoleBean roleBean;

	public RoleServiceImpl(){
		
	}
	
	public String findAll() {
		ResourceBundle labels = roleBean.getLabels();
		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.roles.list"));
		
		roleBean.setListState();
		roleBean.setBreadCrumb(breadCrumb);
		roleBean.setRoles(roleDaoImpl.findAll(Role.class));
		roleBean.setListState();
		roleBean.setPageMessage(roleBean.getLabels().getString("listPage"));
		
		return "manageRoles";
	}
	
	public void prepareCreate(){
		roleBean.clear();
		roleBean.setCreateState();
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.create"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("createPage"));
	}
	
	public void confirmCreate(){
		Role role = new Role();
		role.setName(roleBean.getName());
		role.setDescription(roleBean.getDescription());
		
		roleDaoImpl.save(role);
		
		roleBean.setRoles(roleDaoImpl.findAll(Role.class));
		
		roleBean.clear();
		roleBean.setListState();
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.list"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("listPage"));
	}
	
	public void showDetails(){
		roleBean.setName(roleBean.getRole().getName());
		roleBean.setDescription(roleBean.getRole().getDescription());
		roleBean.setIdRole(roleBean.getRole().getIdRole());
		
		roleBean.setDetailState();
		
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.detail"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("detailPage"));
	}
	
	
	public void prepareUpdate(){
		roleBean.setUpdateState();
		
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.update"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("updatePage"));
	}
	
	public void confirmUpdate(){
		roleBean.getRole().setName(roleBean.getName());
		roleBean.getRole().setDescription(roleBean.getDescription());
		
		roleDaoImpl.update(roleBean.getRole());
		
		roleBean.setListState();
		roleBean.setRoles(roleDaoImpl.findAll(Role.class));
		
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.list"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("listPage"));
	}
	
	public void delete(){
		roleDaoImpl.delete(roleBean.getRole());
		roleBean.setListState();
		roleBean.setRoles(roleDaoImpl.findAll(Role.class));
		
		roleBean.getBreadCrumb().remove(roleBean.getBreadCrumb().size() - 1);
		roleBean.getBreadCrumb().add(roleBean.getLabels().getString("breadCrumb.roles.list"));
		
		roleBean.setPageMessage(roleBean.getLabels().getString("listPage"));
	}
	
	/**
	 * Responsible for go back to the list state
	 */
	public void backToList(){
		roleBean.setListState();
		roleBean.setRoles(roleDaoImpl.findAll(Role.class));
	}
	
	public void update(Role role){
		roleDaoImpl.update(role);
	}
	
	public void delete(Role role){
		roleDaoImpl.delete(role);
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

	public RoleBean getRoleBean() {
		return roleBean;
	}

	public void setRoleBean(RoleBean roleBean) {
		this.roleBean = roleBean;
	}
}
