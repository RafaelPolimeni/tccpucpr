package br.com.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.bean.RecursoBean;
import br.com.dao.PapelDao;
import br.com.dao.RecursoDao;
import br.com.model.Papel;
import br.com.model.Perfil;
import br.com.model.Recurso;
import br.com.service.RecursoService;
import br.com.util.Util;

@Service
@Scope(value = "request")
public class RecursoServiceImpl implements RecursoService {
	@Autowired
	private RecursoBean recursoBean;

	@Autowired
	private RecursoDao recursoDaoImpl;

	@Autowired
	private PapelDao papelDaoImpl;

	ExtendedTableDataModel<Recurso> dataModel;

	public ExtendedTableDataModel<Recurso> getRecursosDataModel() {
		if (dataModel == null) {
			dataModel = new ExtendedTableDataModel<Recurso>(new DataProvider<Recurso>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public Recurso getItemByKey(Object key) {
					for (Recurso c : recursoBean.getRecursos()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<Recurso> getItemsByRange(int firstRow, int endRow) {
					return recursoBean.getRecursos().subList(firstRow, endRow);
				}

				public Object getKey(Recurso item) {
					return item.getIdRecurso();
				}

				public int getRowCount() {
					return recursoBean.getRecursos().size();
				}

			});
		}

		return dataModel;
	}

	public String findAll() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.recurso.list"));

		recursoBean.setBreadCrumb(breadCrumb);
		recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));
		recursoBean.setListState();
		recursoBean.setPageMessage(labels.getString("info.paginaLista"));

		return "gerenciarRecursos";
	}

	public void prepareCreate() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		recursoBean.clear();
		recursoBean.setCreateState();
		recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
		recursoBean.getBreadCrumb().add(labels.getString("breadCrumb.papel.create"));
		recursoBean.setPageMessage(labels.getString("info.paginaInclusao"));

		List<Perfil> autoridades = recursoDaoImpl.findAllPerfis();

		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (Perfil autoridade : (List<Perfil>) autoridades) {
			itens.add(new SelectItem(autoridade.getIdAutoridade(), autoridade.getNomeVisivel()));
		}

		recursoBean.setAutoridades(itens);

		recursoBean.setPossiveisPapeis(papelDaoImpl.findAll(Papel.class));
	}

	public void confirmCreate() throws Exception {
		Recurso recurso = new Recurso();
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		
		if(validate()){
			BeanUtils.copyProperties(recurso, recursoBean);
			recurso.setIdRecurso(null);
			recurso.setLocale("pt_BR");
			Perfil autoridade = new Perfil();
			autoridade.setIdAutoridade(recursoBean.getIdAutoridade());
			recurso.setAutoridade(autoridade);
			recursoDaoImpl.save(recurso);

			recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));

			recursoBean.clear();
			recursoBean.setListState();
			recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
			recursoBean.getBreadCrumb().add(labels.getString("breadCrumb.recurso.list"));

			recursoBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.inclusao")));

			dataModel = new ExtendedTableDataModel<Recurso>(new DataProvider<Recurso>() {
				private static final long serialVersionUID = -5906008834318730281L;

				public Recurso getItemByKey(Object key) {
					for (Recurso c : recursoBean.getRecursos()) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}

				public List<Recurso> getItemsByRange(int firstRow, int endRow) {
					return recursoBean.getRecursos().subList(firstRow, endRow);
				}

				public Object getKey(Recurso item) {
					return item.getIdRecurso();
				}

				public int getRowCount() {
					return recursoBean.getRecursos().size();
				}

			});

			recursoBean.setSelecaoRecurso(null);
		}
	}

	private boolean validate() {
		boolean formularioOK = true;
		ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		FacesContext context = FacesContext.getCurrentInstance();

		if (Util.isEmpty(recursoBean.getNome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.nome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getSobrenome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.sobrenome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (recursoBean.getDataNascimento() == null) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.dataNascimento");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getEmail())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.email");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getTelefone())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.telefone");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (recursoBean.getAutoridade() == null) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.perfil");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getUsuario())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.usuario");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getSenha())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.senha");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(recursoBean.getConfirmarSenha())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("recurso.confirmarSenha");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (!Util.isEmpty(recursoBean.getSenha()) && !Util.isEmpty(recursoBean.getConfirmarSenha())) {
			if (!recursoBean.getSenha().equals(recursoBean.getConfirmarSenha())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, mensagens.getString("validacao.senha")));
				formularioOK = false;
			}
		}

		if (Util.isEmpty(recursoBean.getPapeis())) {
			String mensagem = mensagens.getString("mensagem.validacao.selecaoAoMenosUm");
			String param1 = labels.getString("papel");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		return formularioOK;
	}

	public void showDetail() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iteratorRecursos = recursoBean.getSelecaoRecurso().getKeys();

		if (iteratorRecursos.hasNext()) {
			Object key = iteratorRecursos.next();
			getRecursosDataModel().setRowKey(key);
			recursoBean.setRecurso((Recurso) getRecursosDataModel().getRowData());

			BeanUtils.copyProperties(recursoBean, recursoBean.getRecurso());
			recursoBean.setDetailState();

			recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
			recursoBean.getBreadCrumb().add(labels.getString("breadCrumb.recurso.detail"));

			recursoBean.setPageMessage(labels.getString("info.paginaDetalhe"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("recurso");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void prepareUpdate() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Iterator<Object> iteratorRecursos = recursoBean.getSelecaoRecurso().getKeys();

		if (iteratorRecursos.hasNext()) {
			Object key = iteratorRecursos.next();
			getRecursosDataModel().setRowKey(key);
			recursoBean.setRecurso((Recurso) getRecursosDataModel().getRowData());

			BeanUtils.copyProperties(recursoBean, recursoBean.getRecurso());

			recursoBean.setUpdateState();
			recursoBean.setIdAutoridade(recursoBean.getRecurso().getAutoridade().getIdAutoridade());
			recursoBean.setConfirmarSenha(recursoBean.getSenha());

			List<Perfil> autoridades = recursoDaoImpl.findAllPerfis();

			List<SelectItem> itens = new ArrayList<SelectItem>();

			for (Perfil autoridade : (List<Perfil>) autoridades) {
				itens.add(new SelectItem(autoridade.getIdAutoridade(), autoridade.getNomeVisivel()));
			}

			recursoBean.setAutoridades(itens);
			List<Papel> possiveisPapeis = papelDaoImpl.findAll(Papel.class);

			Iterator<Papel> iteratorPapeis = possiveisPapeis.iterator();

			while (iteratorPapeis.hasNext()) {
				Papel papel = iteratorPapeis.next();

				for (Papel papelAssociado : recursoBean.getPapeis()) {
					if (papelAssociado.getIdPapel().equals(papel.getIdPapel())) {
						iteratorPapeis.remove();
					}
				}
			}

			recursoBean.setPossiveisPapeis(possiveisPapeis);

			recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
			recursoBean.getBreadCrumb().add(labels.getString("breadCrumb.recurso.update"));
			recursoBean.setPageMessage(labels.getString("info.paginaAlteracao"));
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("recurso");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}

	}

	public void confirmUpdate() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

		if(validate()){
			BeanUtils.copyProperties(recursoBean.getRecurso(), recursoBean);
			recursoBean.getRecurso().getAutoridade().setIdAutoridade(recursoBean.getIdAutoridade());
			recursoDaoImpl.update(recursoBean.getRecurso());

			recursoBean.setListState();
			recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));

			recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
			recursoBean.getBreadCrumb().add(labels.getString("breadCrumb.recurso.list"));

			recursoBean.setPageMessage(labels.getString("info.paginaLista"));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.alteracao")));
			recursoBean.setSelecaoRecurso(null);
		}
	}

	/**
	 * Responsible for go back to the list state
	 */
	public void backToList() {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		recursoBean.clear();
		recursoBean.setListState();
		recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));
		recursoBean.setPageMessage(labels.getString("info.paginaLista"));
		
		recursoBean.setSelecaoRecurso(null);
	}

	public List<Perfil> findAllAuthorities() {
		return recursoDaoImpl.findAllPerfis();
	}

	/**
	 * @return the recursoBean
	 */
	public RecursoBean getRecursoBean() {
		return recursoBean;
	}

	/**
	 * @param recursoBean
	 *            the recursoBean to set
	 */
	public void setRecursoBean(RecursoBean recursoBean) {
		this.recursoBean = recursoBean;
	}

	/**
	 * @return the recursoDaoImpl
	 */
	public RecursoDao getRecursoDaoImpl() {
		return recursoDaoImpl;
	}

	/**
	 * @param recursoDaoImpl
	 *            the recursoDaoImpl to set
	 */
	public void setRecursoDaoImpl(RecursoDao recursoDaoImpl) {
		this.recursoDaoImpl = recursoDaoImpl;
	}

	/**
	 * @return the papelDaoImpl
	 */
	public PapelDao getPapelDaoImpl() {
		return papelDaoImpl;
	}

	/**
	 * @param papelDaoImpl
	 *            the papelDaoImpl to set
	 */
	public void setPapelDaoImpl(PapelDao papelDaoImpl) {
		this.papelDaoImpl = papelDaoImpl;
	}
}