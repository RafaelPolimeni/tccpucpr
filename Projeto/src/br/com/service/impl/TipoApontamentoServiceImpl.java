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

import br.com.bean.TipoApontamentoBean;
import br.com.dao.TipoApontamentoDao;
import br.com.model.TipoApontamento;
import br.com.service.TipoApontamentoService;
import br.com.util.Util;

@Service
@Scope(value = "request")
public class TipoApontamentoServiceImpl implements TipoApontamentoService {

	@Autowired
	private TipoApontamentoDao tipoApontamentoDaoImpl;
	@Autowired
	private TipoApontamentoBean tipoApontamentoBean;

	ExtendedTableDataModel<TipoApontamento> dataModel;

	public TipoApontamentoServiceImpl() {

	}

	public String findAll() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.tipoApontamento.list"));

		
		tipoApontamentoBean.setTiposApontamentos(tipoApontamentoDaoImpl.findAll(TipoApontamento.class));
		
		tipoApontamentoBean.setBreadCrumb(breadCrumb);
		tipoApontamentoBean.setListState();
		tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));

		return "gerenciarTipoApontamento";
	}

	public void prepareCreate() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		tipoApontamentoBean.clear();
		tipoApontamentoBean.setCreateState();
		tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
		tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.create"));

		tipoApontamentoBean.setPageMessage(labels.getString("info.paginaInclusao"));

		tipoApontamentoBean.setSelecaoTipoApontamento(null);
	}

	public void confirmCreate() {
		if (validate()) {
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			TipoApontamento tipoApontamento = new TipoApontamento();
			tipoApontamento.setNome(tipoApontamentoBean.getNome());
			tipoApontamento.setDescricao(tipoApontamentoBean.getDescricao());

			tipoApontamentoDaoImpl.save(tipoApontamento);

			tipoApontamentoBean.setTiposApontamentos(tipoApontamentoDaoImpl.findAll(TipoApontamento.class));

			tipoApontamentoBean.clear();
			tipoApontamentoBean.setListState();
			tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
			tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.list"));

			tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.inclusao")));

			dataModel = new ExtendedTableDataModel<TipoApontamento>(new DataProvider<TipoApontamento>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public TipoApontamento getItemByKey(Object key) {
					for (TipoApontamento c : tipoApontamentoBean.getTiposApontamentos()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<TipoApontamento> getItemsByRange(int firstRow, int endRow) {
					return tipoApontamentoBean.getTiposApontamentos().subList(firstRow, endRow);
				}

				public Object getKey(TipoApontamento item) {
					return item.getIdTipoApontamento();
				}

				public int getRowCount() {
					return tipoApontamentoBean.getTiposApontamentos().size();
				}

			});
		}
	}

	public void showDetails() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iterator = tipoApontamentoBean.getSelecaoTipoApontamento().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getTiposApontamentoDataModel().setRowKey(key);
			tipoApontamentoBean.setTipoApontamento((TipoApontamento) getTiposApontamentoDataModel().getRowData());

			BeanUtils.copyProperties(tipoApontamentoBean, tipoApontamentoBean.getTipoApontamento());

			tipoApontamentoBean.setNome(tipoApontamentoBean.getTipoApontamento().getNome());
			tipoApontamentoBean.setDescricao(tipoApontamentoBean.getTipoApontamento().getDescricao());
			tipoApontamentoBean.setIdTipoApontamento(tipoApontamentoBean.getTipoApontamento().getIdTipoApontamento());

			tipoApontamentoBean.setDetailState();

			tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
			tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.detail"));

			tipoApontamentoBean.setPageMessage(labels.getString("info.paginaDetalhe"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("tipoApontamento");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void prepareUpdate() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iterator = tipoApontamentoBean.getSelecaoTipoApontamento().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getTiposApontamentoDataModel().setRowKey(key);
			tipoApontamentoBean.setTipoApontamento((TipoApontamento) getTiposApontamentoDataModel().getRowData());
			BeanUtils.copyProperties(tipoApontamentoBean, tipoApontamentoBean.getTipoApontamento());

			tipoApontamentoBean.setUpdateState();

			tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
			tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.update"));

			tipoApontamentoBean.setPageMessage(labels.getString("info.paginaAlteracao"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("tipoApontamento");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public ExtendedTableDataModel<TipoApontamento> getTiposApontamentoDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<TipoApontamento>(new DataProvider<TipoApontamento>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public TipoApontamento getItemByKey(Object key) {
					for (TipoApontamento c : tipoApontamentoBean.getTiposApontamentos()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<TipoApontamento> getItemsByRange(int firstRow, int endRow) {
					return tipoApontamentoBean.getTiposApontamentos().subList(firstRow, endRow);
				}

				public Object getKey(TipoApontamento item) {
					return item.getIdTipoApontamento();
				}

				public int getRowCount() {
					return tipoApontamentoBean.getTiposApontamentos().size();
				}

			});
		}

		return dataModel;
	}

	public void confirmUpdate() {
		if (validate()) {
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			tipoApontamentoBean.getTipoApontamento().setNome(tipoApontamentoBean.getNome());
			tipoApontamentoBean.getTipoApontamento().setDescricao(tipoApontamentoBean.getDescricao());

			tipoApontamentoDaoImpl.update(tipoApontamentoBean.getTipoApontamento());

			tipoApontamentoBean.setListState();
			tipoApontamentoBean.setTiposApontamentos(tipoApontamentoDaoImpl.findAll(TipoApontamento.class));

			tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
			tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.list"));

			tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.alteracao")));
			tipoApontamentoBean.setSelecaoTipoApontamento(null);
		}
	}

	public void prepareDelete() {
		Iterator<Object> iterator = tipoApontamentoBean.getSelecaoTipoApontamento().getKeys();

		if (iterator.hasNext()) {
			Object key = iterator.next();
			getTiposApontamentoDataModel().setRowKey(key);
			tipoApontamentoBean.setTipoApontamento((TipoApontamento) getTiposApontamentoDataModel().getRowData());

			tipoApontamentoBean.setShowModal(true);
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("tipoApontamento");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void cancelDelete() {
		tipoApontamentoBean.setShowModal(false);
	}

	@Transactional
	public void delete() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

		try {
			tipoApontamentoDaoImpl.delete(tipoApontamentoBean.getTipoApontamento());
			tipoApontamentoBean.setListState();
			tipoApontamentoBean.setTiposApontamentos(tipoApontamentoDaoImpl.findAll(TipoApontamento.class));

			tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
			tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.list"));

			tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));

			tipoApontamentoBean.setShowModal(false);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.exclusao")));
			tipoApontamentoBean.setSelecaoTipoApontamento(null);

			dataModel = new ExtendedTableDataModel<TipoApontamento>(new DataProvider<TipoApontamento>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public TipoApontamento getItemByKey(Object key) {
					for (TipoApontamento c : tipoApontamentoBean.getTiposApontamentos()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<TipoApontamento> getItemsByRange(int firstRow, int endRow) {
					return tipoApontamentoBean.getTiposApontamentos().subList(firstRow, endRow);
				}

				public Object getKey(TipoApontamento item) {
					return item.getIdTipoApontamento();
				}

				public int getRowCount() {
					return tipoApontamentoBean.getTiposApontamentos().size();
				}

			});
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, null, messages.getString("mensagem.tipoApontamento.tipoApontamentoReferenciado")));

				tipoApontamentoBean.setListState();
				tipoApontamentoBean.getBreadCrumb().remove(tipoApontamentoBean.getBreadCrumb().size() - 1);
				tipoApontamentoBean.getBreadCrumb().add(labels.getString("breadCrumb.tipoApontamento.list"));
				tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));
				tipoApontamentoBean.setShowModal(false);
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

		if (Util.isEmpty(tipoApontamentoBean.getNome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("tipoApontamento.nome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(tipoApontamentoBean.getDescricao())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("tipoApontamento.descricao");

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

		tipoApontamentoBean.clear();
		tipoApontamentoBean.setListState();
		tipoApontamentoBean.setPageMessage(labels.getString("info.paginaLista"));

		tipoApontamentoBean.setSelecaoTipoApontamento(null);
	}

	/**
	 * @return the tipoApontamentoDaoImpl
	 */
	public TipoApontamentoDao getTipoApontamentoDaoImpl() {
		return tipoApontamentoDaoImpl;
	}

	/**
	 * @param tipoApontamentoDaoImpl
	 *            the tipoApontamentoDaoImpl to set
	 */
	public void setTipoApontamentoDaoImpl(TipoApontamentoDao tipoApontamentoDaoImpl) {
		this.tipoApontamentoDaoImpl = tipoApontamentoDaoImpl;
	}

	/**
	 * @return the tipoApontamentoBean
	 */
	public TipoApontamentoBean getTipoApontamentoBean() {
		return tipoApontamentoBean;
	}

	/**
	 * @param tipoApontamentoBean
	 *            the tipoApontamentoBean to set
	 */
	public void setTipoApontamentoBean(TipoApontamentoBean tipoApontamentoBean) {
		this.tipoApontamentoBean = tipoApontamentoBean;
	}
}
