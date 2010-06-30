package br.com.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bean.PapelBean;
import br.com.dao.PapelDao;
import br.com.model.Papel;
import br.com.service.PapelService;
import br.com.util.Util;

@Service
@Scope(value = "request")
public class PapelServiceImpl implements PapelService {

	@Autowired
	private PapelDao roleDaoImpl;
	@Autowired
	private PapelBean papelBean;

	ExtendedTableDataModel<Papel> dataModel;

	public PapelServiceImpl() {

	}

	public String findAll() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.papel.list"));

		papelBean.setBreadCrumb(breadCrumb);
		papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));
		papelBean.setListState();
		papelBean.setPageMessage(labels.getString("info.paginaLista"));

		return "gerenciarPapeis";
	}

	public void prepareCreate() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		papelBean.clear();
		papelBean.setCreateState();
		papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
		papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.create"));

		papelBean.setPageMessage(labels.getString("info.paginaInclusao"));
		
		papelBean.setSelecaoPapeis(null);
	}

	public void confirmCreate() {
		if(validate()){
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			Papel papel = new Papel();
			papel.setNome(papelBean.getNome());
			papel.setDescricao(papelBean.getDescricao());

			roleDaoImpl.save(papel);

			papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));

			papelBean.clear();
			papelBean.setListState();
			papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
			papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.list"));

			papelBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.inclusao")));
			
			dataModel = new ExtendedTableDataModel<Papel>(new DataProvider<Papel>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public Papel getItemByKey(Object key) {
					for (Papel c : papelBean.getPapeis()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<Papel> getItemsByRange(int firstRow, int endRow) {
					return papelBean.getPapeis().subList(firstRow, endRow);
				}

				public Object getKey(Papel item) {
					return item.getIdPapel();
				}

				public int getRowCount() {
					return papelBean.getPapeis().size();
				}

			});
		}
	}

	public void showDetails() throws Exception{
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		
		Iterator<Object> iterator = papelBean.getSelecaoPapeis().getKeys();
		
		if (iterator.hasNext()) {
			Object key = iterator.next();
			getPapeisDataModel().setRowKey(key);
			papelBean.setPapel((Papel) getPapeisDataModel().getRowData());
			BeanUtils.copyProperties(papelBean, papelBean.getPapel());
			
			papelBean.setNome(papelBean.getPapel().getNome());
			papelBean.setDescricao(papelBean.getPapel().getDescricao());
			papelBean.setIdPapel(papelBean.getPapel().getIdPapel());

			papelBean.setDetailState();

			papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
			papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.detail"));

			papelBean.setPageMessage(labels.getString("info.paginaDetalhe"));
		}else{
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("papel");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void prepareUpdate() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iterator = papelBean.getSelecaoPapeis().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getPapeisDataModel().setRowKey(key);
			papelBean.setPapel((Papel) getPapeisDataModel().getRowData());
			BeanUtils.copyProperties(papelBean, papelBean.getPapel());

			papelBean.getPapeis();

			papelBean.setUpdateState();

			papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
			papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.update"));

			papelBean.setPageMessage(labels.getString("info.paginaAlteracao"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("papel");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public ExtendedTableDataModel<Papel> getPapeisDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<Papel>(new DataProvider<Papel>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public Papel getItemByKey(Object key) {
					for (Papel c : papelBean.getPapeis()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<Papel> getItemsByRange(int firstRow, int endRow) {
					return papelBean.getPapeis().subList(firstRow, endRow);
				}

				public Object getKey(Papel item) {
					return item.getIdPapel();
				}

				public int getRowCount() {
					return papelBean.getPapeis().size();
				}

			});
		}
		
		return dataModel;
	}

	public void confirmUpdate() {
		if(validate()){
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
	
			papelBean.getPapel().setNome(papelBean.getNome());
			papelBean.getPapel().setDescricao(papelBean.getDescricao());
	
			roleDaoImpl.update(papelBean.getPapel());
	
			papelBean.setListState();
	
			papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
			papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.list"));
	
			papelBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.alteracao")));
			papelBean.setSelecaoPapeis(null);
		}
	}

	public void prepareDelete() {
		Iterator<Object> iterator = papelBean.getSelecaoPapeis().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getPapeisDataModel().setRowKey(key);
			papelBean.setPapel((Papel) getPapeisDataModel().getRowData());

			papelBean.setShowModal(true);
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("papel");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void cancelDelete() {
		papelBean.setShowModal(false);
	}

	@Transactional
	public void delete() throws Exception{
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		
		try{
			roleDaoImpl.delete(papelBean.getPapel());
			papelBean.setListState();
			papelBean.setPapeis(roleDaoImpl.findAll(Papel.class));

			papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
			papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.list"));

			papelBean.setPageMessage(labels.getString("info.paginaLista"));

			papelBean.setShowModal(false);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.exclusao")));
			papelBean.setSelecaoPapeis(null);
			
			dataModel = new ExtendedTableDataModel<Papel>(new DataProvider<Papel>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public Papel getItemByKey(Object key) {
					for (Papel c : papelBean.getPapeis()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<Papel> getItemsByRange(int firstRow, int endRow) {
					return papelBean.getPapeis().subList(firstRow, endRow);
				}

				public Object getKey(Papel item) {
					return item.getIdPapel();
				}

				public int getRowCount() {
					return papelBean.getPapeis().size();
				}

			});	
		}catch (Exception e) {
			if(e.getCause() instanceof ConstraintViolationException){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, messages.getString("mensagem.papeis.papelReferenciado")));
				
				papelBean.setListState();
	
				papelBean.getBreadCrumb().remove(papelBean.getBreadCrumb().size() - 1);
				papelBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.list"));
	
				papelBean.setPageMessage(labels.getString("info.paginaLista"));
	
				papelBean.setShowModal(false);
			}else{
				throw e;
			}
		}
	}
	
	private boolean validate(){
		boolean formularioOK = true;
		ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		FacesContext context = FacesContext.getCurrentInstance();

		if (Util.isEmpty(papelBean.getNome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("papel.nome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}
		
		if (Util.isEmpty(papelBean.getDescricao())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("papel.descricao");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}
		
		return formularioOK;
	}

	/**
	 * Responsible for go back to the list state
	 */
	public void backToList() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		papelBean.clear();
		papelBean.setListState();
		papelBean.setPageMessage(labels.getString("info.paginaLista"));
		
		papelBean.setSelecaoPapeis(null);
	}

	public void update(Papel role) {
		roleDaoImpl.update(role);
	}

	public void delete(Papel role) {
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
