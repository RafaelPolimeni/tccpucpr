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

import br.com.bean.StatusAtividadeBean;
import br.com.dao.StatusAtividadeDao;
import br.com.model.StatusAtividade;
import br.com.service.StatusAtividadeService;
import br.com.util.Util;

@Service
@Scope(value = "request")
public class StatusAtividadeServiceImpl implements StatusAtividadeService {

	@Autowired
	private StatusAtividadeDao statusAtividadeDaoImpl;
	@Autowired
	private StatusAtividadeBean statusAtividadeBean;

	ExtendedTableDataModel<StatusAtividade> dataModel;

	public StatusAtividadeServiceImpl() {

	}

	public String findAll() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.statusAtividade.list"));

		statusAtividadeBean.setBreadCrumb(breadCrumb);
		statusAtividadeBean.setStatus(statusAtividadeDaoImpl.findAll(StatusAtividade.class));
		statusAtividadeBean.setListState();
		statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));

		return "gerenciarStatus";
	}

	public void prepareCreate() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		statusAtividadeBean.clear();
		statusAtividadeBean.setCreateState();
		statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
		statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.create"));

		statusAtividadeBean.setPageMessage(labels.getString("info.paginaInclusao"));

		statusAtividadeBean.setSelecaoStatus(null);
	}

	public void confirmCreate() {
		if (validate()) {
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			StatusAtividade status = new StatusAtividade();
			status.setNome(statusAtividadeBean.getNome());
			status.setDescricao(statusAtividadeBean.getDescricao());

			statusAtividadeDaoImpl.save(status);

			statusAtividadeBean.setStatus(statusAtividadeDaoImpl.findAll(StatusAtividade.class));

			statusAtividadeBean.clear();
			statusAtividadeBean.setListState();
			statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
			statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.list"));

			statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.inclusao")));

			dataModel = new ExtendedTableDataModel<StatusAtividade>(new DataProvider<StatusAtividade>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public StatusAtividade getItemByKey(Object key) {
					for (StatusAtividade c : statusAtividadeBean.getStatus()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<StatusAtividade> getItemsByRange(int firstRow, int endRow) {
					return statusAtividadeBean.getStatus().subList(firstRow, endRow);
				}

				public Object getKey(StatusAtividade item) {
					return item.getIdStatusAtividade();
				}

				public int getRowCount() {
					return statusAtividadeBean.getStatus().size();
				}

			});
		}
	}

	public void showDetails() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iterator = statusAtividadeBean.getSelecaoStatus().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getStatusDataModel().setRowKey(key);
			statusAtividadeBean.setStatusAtividade((StatusAtividade) getStatusDataModel().getRowData());
			BeanUtils.copyProperties(statusAtividadeBean, statusAtividadeBean.getStatusAtividade());

			statusAtividadeBean.setDetailState();

			statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
			statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.detail"));

			statusAtividadeBean.setPageMessage(labels.getString("info.paginaDetalhe"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("statusAtividade");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void prepareUpdate() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iterator = statusAtividadeBean.getSelecaoStatus().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getStatusDataModel().setRowKey(key);
			statusAtividadeBean.setStatusAtividade((StatusAtividade) getStatusDataModel().getRowData());
			BeanUtils.copyProperties(statusAtividadeBean, statusAtividadeBean.getStatusAtividade());

			statusAtividadeBean.setUpdateState();

			statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
			statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.update"));

			statusAtividadeBean.setPageMessage(labels.getString("info.paginaAlteracao"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("statusAtividade");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public ExtendedTableDataModel<StatusAtividade> getStatusDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<StatusAtividade>(new DataProvider<StatusAtividade>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public StatusAtividade getItemByKey(Object key) {
					for (StatusAtividade c : statusAtividadeBean.getStatus()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<StatusAtividade> getItemsByRange(int firstRow, int endRow) {
					return statusAtividadeBean.getStatus().subList(firstRow, endRow);
				}

				public Object getKey(StatusAtividade item) {
					return item.getIdStatusAtividade();
				}

				public int getRowCount() {
					return statusAtividadeBean.getStatus().size();
				}

			});
		}

		return dataModel;
	}

	public void confirmUpdate() {
		if (validate()) {
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			statusAtividadeBean.getStatusAtividade().setNome(statusAtividadeBean.getNome());
			statusAtividadeBean.getStatusAtividade().setDescricao(statusAtividadeBean.getDescricao());

			statusAtividadeDaoImpl.update(statusAtividadeBean.getStatusAtividade());

			statusAtividadeBean.setListState();

			statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
			statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.list"));

			statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.alteracao")));
			statusAtividadeBean.setSelecaoStatus(null);
		}
	}

	public void prepareDelete() {
		Iterator<Object> iterator = statusAtividadeBean.getSelecaoStatus().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getStatusDataModel().setRowKey(key);
			statusAtividadeBean.setStatusAtividade((StatusAtividade) getStatusDataModel().getRowData());

			statusAtividadeBean.setShowModal(true);
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("statusAtividade");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void cancelDelete() {
		statusAtividadeBean.setShowModal(false);
	}

	@Transactional
	public void delete() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

		try {
			statusAtividadeDaoImpl.delete(statusAtividadeBean.getStatusAtividade());
			statusAtividadeBean.setListState();
			statusAtividadeBean.setStatus(statusAtividadeDaoImpl.findAll(StatusAtividade.class));

			statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
			statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.list"));

			statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));

			statusAtividadeBean.setShowModal(false);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.exclusao")));
			statusAtividadeBean.setSelecaoStatus(null);

			dataModel = new ExtendedTableDataModel<StatusAtividade>(new DataProvider<StatusAtividade>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public StatusAtividade getItemByKey(Object key) {
					for (StatusAtividade c : statusAtividadeBean.getStatus()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<StatusAtividade> getItemsByRange(int firstRow, int endRow) {
					return statusAtividadeBean.getStatus().subList(firstRow, endRow);
				}

				public Object getKey(StatusAtividade item) {
					return item.getIdStatusAtividade();
				}

				public int getRowCount() {
					return statusAtividadeBean.getStatus().size();
				}

			});
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, messages.getString("mensagem.papeis.statusReferenciado")));

				statusAtividadeBean.setListState();

				statusAtividadeBean.getBreadCrumb().remove(statusAtividadeBean.getBreadCrumb().size() - 1);
				statusAtividadeBean.getBreadCrumb().add(labels.getString("breadCrumb.statusAtividade.list"));

				statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));

				statusAtividadeBean.setShowModal(false);
			} else {
				throw e;
			}
		}
	}

	private boolean validate() {
		boolean formularioOK = true;
		ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		FacesContext context = FacesContext.getCurrentInstance();

		if (Util.isEmpty(statusAtividadeBean.getNome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("statusAtividade.nome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(statusAtividadeBean.getDescricao())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("statusAtividade.descricao");

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

		statusAtividadeBean.clear();
		statusAtividadeBean.setListState();
		statusAtividadeBean.setPageMessage(labels.getString("info.paginaLista"));

		statusAtividadeBean.setSelecaoStatus(null);
	}

	/**
	 * @return the statusAtividadeDaoImpl
	 */
	public StatusAtividadeDao getStatusAtividadeDaoImpl() {
		return statusAtividadeDaoImpl;
	}

	/**
	 * @param statusAtividadeDaoImpl
	 *            the statusAtividadeDaoImpl to set
	 */
	public void setStatusAtividadeDaoImpl(StatusAtividadeDao statusAtividadeDaoImpl) {
		this.statusAtividadeDaoImpl = statusAtividadeDaoImpl;
	}

	/**
	 * @return the statusAtividadeBean
	 */
	public StatusAtividadeBean getStatusAtividadeBean() {
		return statusAtividadeBean;
	}

	/**
	 * @param statusAtividadeBean
	 *            the statusAtividadeBean to set
	 */
	public void setStatusAtividadeBean(StatusAtividadeBean statusAtividadeBean) {
		this.statusAtividadeBean = statusAtividadeBean;
	}
}