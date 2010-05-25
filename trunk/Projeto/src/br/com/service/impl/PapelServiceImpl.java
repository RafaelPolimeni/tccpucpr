package br.com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.bean.PapelBean;
import br.com.dao.PapelDao;
import br.com.model.Papel;
import br.com.service.PapelService;

@Service
@Scope(value="request")
public class PapelServiceImpl implements PapelService {

	@Autowired
	private PapelDao roleDaoImpl;
	@Autowired
	private PapelBean papelBean;

	public PapelServiceImpl(){
		
	}
	
	public String findAll() {
		ResourceBundle labels = papelBean.getLabels();
		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.papel.list"));
		
		papelBean.setBreadCrumb(breadCrumb);
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		papelBean.setListState();
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaLista"));
		
		return "manageRoles";
	}
	
	public void prepareCreate(){
		papelBean.clear();
		papelBean.setCreateState();
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.create"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaInclusao"));
	}
	
	public void confirmCreate(){
		Papel papel = new Papel();
		papel.setNome(papelBean.getNome());
		papel.setDescricao(papelBean.getDescricao());
		
		roleDaoImpl.save(papel);
		
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		
		papelBean.clear();
		papelBean.setListState();
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.list"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaLista"));
	}
	
	public void showDetails(){
		papelBean.setNome(papelBean.getPapel().getNome());
		papelBean.setDescricao(papelBean.getPapel().getDescricao());
		papelBean.setIdPapel(papelBean.getPapel().getIdPapel());
		
		papelBean.setDetailState();
		
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.detail"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaDetalhe"));
	}
	
	
	public void prepareUpdate(){
		papelBean.setUpdateState();
		
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.update"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaAlteracao"));
	}
	
	public void confirmUpdate(){
		papelBean.getPapel().setNome(papelBean.getNome());
		papelBean.getPapel().setDescricao(papelBean.getDescricao());
		
		roleDaoImpl.update(papelBean.getPapel());
		
		papelBean.setListState();
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.list"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaLista"));
	}
	
	public void prepareDelete(){
		papelBean.setShowModal(true);
	}
	
	public void cancelDelete(){
		papelBean.setShowModal(false);
	}
	
	public void delete(){
		roleDaoImpl.delete(papelBean.getPapel());
		papelBean.setListState();
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(papelBean.getLabels().getString("breadCrumb.papel.list"));
		
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaLista"));
		
		papelBean.setShowModal(false);
	}
	
	/**
	 * Responsible for go back to the list state
	 */
	public void backToList(){
		papelBean.clear();
		papelBean.setListState();
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		papelBean.setPageMessage(papelBean.getLabels().getString("info.paginaLista"));
	}
	
	public void update(Papel role){
		roleDaoImpl.update(role);
	}
	
	public void delete(Papel role){
		roleDaoImpl.delete(role);
	}

	/**
	 * @return the roleDaoImpl
	 */
	public PapelDao getRoleDaoImpl() {
		return roleDaoImpl;
	}

	/**
	 * @param roleDaoImpl
	 *            the roleDaoImpl to set
	 */
	public void setRoleDaoImpl(PapelDao roleDaoImpl) {
		this.roleDaoImpl = roleDaoImpl;
	}

	public PapelBean getPapelBean() {
		return papelBean;
	}

	public void setPapelBean(PapelBean papelBean) {
		this.papelBean = papelBean;
	}
}
