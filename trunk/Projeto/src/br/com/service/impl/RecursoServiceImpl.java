package br.com.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;
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

	public String findAll() {
		ResourceBundle labels = recursoBean.getLabels();
		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.recurso.list"));

		recursoBean.setBreadCrumb(breadCrumb);
		recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));
		recursoBean.setListState();
		recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaLista"));

		return "gerenciarRecursos";
	}

	public void prepareCreate() {
		recursoBean.clear();
		recursoBean.setCreateState();
		recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
		recursoBean.getBreadCrumb().add(recursoBean.getLabels().getString("breadCrumb.papel.create"));
		recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaInclusao"));

		List<Perfil> autoridades = recursoDaoImpl.findAllPerfis();

		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (Perfil autoridade : (List<Perfil>) autoridades) {
			itens.add(new SelectItem(autoridade.getIdAutoridade(), autoridade.getNomeVisivel()));
		}

		recursoBean.setAutoridades(itens);

		recursoBean.setPossiveisPapeis(papelDaoImpl.findAll(Papel.class));
	}

	public void confirmCreate() {
		Recurso recurso = new Recurso();
		try {
			if (recursoBean.getSenha().equals(recursoBean.getConfirmarSenha())) {
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
				recursoBean.getBreadCrumb().add(recursoBean.getLabels().getString("breadCrumb.recurso.list"));

				recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaLista"));
			} else {
				recursoBean.setSenha("");
				recursoBean.setConfirmarSenha("");
				FacesContext context = FacesContext.getCurrentInstance();
				UIComponent senha = Util.findComponent(context.getViewRoot(), "senha");
				context.addMessage(senha.getClientId(context), new FacesMessage(recursoBean.getMessages().getString("validacao.senha")));
			}
		} catch (Exception e) {

		}
	}

	public void showDetail() {
		try {
			BeanUtils.copyProperties(recursoBean, recursoBean.getRecurso());
			recursoBean.setDetailState();

			recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
			recursoBean.getBreadCrumb().add(recursoBean.getLabels().getString("breadCrumb.recurso.detail"));

			recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaDetalhe"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareUpdate() {
		recursoBean.setUpdateState();
		recursoBean.setIdAutoridade(recursoBean.getAutoridade().getIdAutoridade());
		recursoBean.setConfirmarSenha(recursoBean.getSenha());

		List<Perfil> autoridades = recursoDaoImpl.findAllPerfis();

		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (Perfil autoridade : (List<Perfil>) autoridades) {
			itens.add(new SelectItem(autoridade.getIdAutoridade(), autoridade.getNomeVisivel()));
		}

		recursoBean.setAutoridades(itens);
		List<Papel> possiveisPapeis = papelDaoImpl.findAll(Papel.class);

		Iterator<Papel> iterator = possiveisPapeis.iterator();

		while (iterator.hasNext()) {
			Papel papel = iterator.next();

			for (Papel papelAssociado : recursoBean.getPapeis()) {
				if (papelAssociado.getIdPapel().equals(papel.getIdPapel())) {
					iterator.remove();
//					possiveisPapeis.remove(papel);
				}
			}
		}

		recursoBean.setPossiveisPapeis(possiveisPapeis);

		recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
		recursoBean.getBreadCrumb().add(recursoBean.getLabels().getString("breadCrumb.recurso.update"));
		recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaAlteracao"));
	}

	public void confirmUpdate() {
		if (recursoBean.getSenha().equals(recursoBean.getConfirmarSenha())) {
			try {
				BeanUtils.copyProperties(recursoBean.getRecurso(), recursoBean);
				recursoBean.getRecurso().getAutoridade().setIdAutoridade(recursoBean.getIdAutoridade());
				recursoDaoImpl.update(recursoBean.getRecurso());

				recursoBean.setListState();
				recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));

				recursoBean.getBreadCrumb().remove(recursoBean.getBreadCrumb().size() - 1);
				recursoBean.getBreadCrumb().add(recursoBean.getLabels().getString("breadCrumb.recurso.list"));

				recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaLista"));
			} catch (Exception e) {

			}
		} else {
			recursoBean.setSenha("");
			recursoBean.setConfirmarSenha("");
			FacesContext context = FacesContext.getCurrentInstance();
			UIComponent senha = Util.findComponent(context.getViewRoot(), "senha");
			context.addMessage(senha.getClientId(context), new FacesMessage(recursoBean.getMessages().getString("validacao.senha")));
		}
	}

	/**
	 * Responsible for go back to the list state
	 */
	public void backToList() {
		recursoBean.clear();
		recursoBean.setListState();
		recursoBean.setRecursos(recursoDaoImpl.findAll(Recurso.class));
		recursoBean.setPageMessage(recursoBean.getLabels().getString("info.paginaLista"));
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