package br.com.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bean.ProjetoBean;
import br.com.bean.UserBean;
import br.com.dao.PapelDao;
import br.com.dao.ProjetoDao;
import br.com.dao.RecursoDao;
import br.com.model.HistoricoProjeto;
import br.com.model.Papel;
import br.com.model.Projeto;
import br.com.model.Recurso;
import br.com.model.RecursoProjeto;
import br.com.model.RecursoProjetoPK;
import br.com.service.ProjetoService;
import br.com.util.Util;

@Service
@Scope(value = "request")
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoDao projetoDaoImpl;

	@Autowired
	private RecursoDao recursoDaoImpl;

	@Autowired
	private PapelDao papelDaoImpl;

	@Autowired
	private ProjetoBean projetoBean;

	// public List<SelectItem> papeis;

	public String findAll() throws Exception {
		try {
			projetoBean.setProjetos(projetoDaoImpl.findAll(Projeto.class));

			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			List<String> breadCrumb = new ArrayList<String>();
			breadCrumb.add(labels.getString("breadCrumb.homePage"));
			breadCrumb.add(labels.getString("breadCrumb.maintenance"));
			breadCrumb.add(labels.getString("breadCrumb.projeto.list"));

			projetoBean.setBreadCrumb(breadCrumb);
			projetoBean.setListState();
			projetoBean.setPageMessage(labels.getString("info.paginaLista"));

			return "gerenciarProjetos";
		} catch (Exception e) {
			return "gerenciarProjetos";
		}
	}

	public void prepareCreate(ActionEvent event) throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		projetoBean.clear();
		projetoBean.setCreateState();
		projetoBean.getBreadCrumb().remove(projetoBean.getBreadCrumb().size() - 1);
		projetoBean.getBreadCrumb().add(labels.getString("breadCrumb.projeto.create"));
		projetoBean.setPageMessage(labels.getString("info.paginaInclusao"));
	}

	public void confirmCreate(ActionEvent event) throws Exception {
		if (validate()) {
			Projeto projeto = new Projeto();

			projeto.setNome(projetoBean.getNome());
			projeto.setDescricao(projetoBean.getDescricao());
			projeto.setDataInicioPrevista(projetoBean.getDataInicioPrevista());
			projeto.setDataFimPrevista(projetoBean.getDataFimPrevista());
			projeto.setCriador(projetoBean.getCriador());
			projeto.setGerentes(projetoBean.getGerentes());
			projeto.setRecursos(projetoBean.getRecursos());
			projeto.setObservadores(projetoBean.getObservadores());

			projeto.setStatus(Projeto.PROJETO_CRIADO);
			projeto.setIdProjeto(null);

			UserBean userBean = (UserBean) Util.getFromSession("userBean");

			projeto.setCriador(recursoDaoImpl.find(Recurso.class, userBean.getIdRecurso()));

			List<RecursoProjeto> recursos = projetoBean.getRecursos();
			Date data = new Date();

			List<HistoricoProjeto> historicos = new ArrayList<HistoricoProjeto>();

			for (RecursoProjeto recursoProjeto : recursos) {
				HistoricoProjeto historico = new HistoricoProjeto();
				historico.setProjeto(projeto);
				historico.setRecurso(recursoProjeto.getRecursoProjetoPK().getRecurso());
				historico.setData(data);
				historico.setAdicionou(true);
				historicos.add(historico);

				recursoProjeto.getRecursoProjetoPK().setProjeto(projeto);
			}

			projeto.setHistoricos(historicos);
			projetoDaoImpl.save(projeto);

			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");

			projetoBean.clear();
			projetoBean.setListState();
			projetoBean.getBreadCrumb().remove(projetoBean.getBreadCrumb().size() - 1);
			projetoBean.getBreadCrumb().add(labels.getString("breadCrumb.projeto.list"));
			projetoBean.setPageMessage(labels.getString("info.paginaLista"));

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages.getString("info.sucesso.inclusao")));
		}
	}

	private boolean validate() throws Exception {
		boolean formularioOK = true;
		ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		FacesContext context = FacesContext.getCurrentInstance();

		if (Util.isEmpty(projetoBean.getNome())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("projeto.nome");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(projetoBean.getDescricao())) {
			String mensagem = mensagens.getString("mensagem.validacao.obrigatorio");
			String param1 = labels.getString("projeto.descricao");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(projetoBean.getGerentes())) {
			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("projeto.gerente");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		if (Util.isEmpty(projetoBean.getRecursos())) {
			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("projeto.recurso");

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
			formularioOK = false;
		}

		return formularioOK;
	}

	public void excluirObservador() throws Exception {
		projetoBean.getObservadores().remove(projetoBean.getRecursoTemp());

		List<Recurso> possiveisObservadores = recursoDaoImpl.consultarObservadores();

		if (projetoBean.getObservadores() != null) {
			for (Recurso recurso : projetoBean.getObservadores()) {
				possiveisObservadores.remove(recurso);
			}
		}

		projetoBean.setPossiveisObservadores(possiveisObservadores);
	}

	public void excluirGerente() throws Exception {
		projetoBean.getGerentes().remove(projetoBean.getRecursoTemp());

		List<Recurso> possiveisGerentes = recursoDaoImpl.consultarGerentes();

		if (projetoBean.getGerentes() != null) {
			for (Recurso recurso : projetoBean.getGerentes()) {
				possiveisGerentes.remove(recurso);
			}
		}

		projetoBean.setPossiveisGerentes(possiveisGerentes);
	}

	public void excluirRecurso() throws Exception {
		projetoBean.getRecursos().remove(projetoBean.getRecursoProjetoTemp());

		List<Recurso> possiveisRecursos = recursoDaoImpl.consultarHabilitados();

		if (projetoBean.getRecursos() != null) {
			for (RecursoProjeto recurso : projetoBean.getRecursos()) {
				possiveisRecursos.remove(recurso.getRecursoProjetoPK().getRecurso());
			}
		}

		projetoBean.setPossiveisRecursos(possiveisRecursos);
	}

	public void selecionarObservadores(ActionEvent event) throws Exception {
		Iterator iterator = projetoBean.getSelecaoObservadores().getKeys();

		List<Recurso> recursosSelecionados = new ArrayList();

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			recursosSelecionados.add(projetoBean.getPossiveisObservadores().get(index));
		}

		if (recursosSelecionados.size() != 0) {
			if (projetoBean.getObservadores() == null) {
				projetoBean.setObservadores(recursosSelecionados);
			} else {
				projetoBean.getObservadores().addAll(recursosSelecionados);
			}

			projetoBean.setSelecaoObservadores(null);

			List<Recurso> possiveisObservadores = recursoDaoImpl.consultarObservadores();

			if (projetoBean.getObservadores() != null) {
				for (Recurso recurso : projetoBean.getObservadores()) {
					possiveisObservadores.remove(recurso);
				}
			}

			projetoBean.setPossiveisObservadores(possiveisObservadores);
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("projeto.observador");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void selecionarGerentes(ActionEvent event) throws Exception {
		Iterator iterator = projetoBean.getSelecaoGerentes().getKeys();

		List<Recurso> gerentesSelecionados = new ArrayList();

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			gerentesSelecionados.add(projetoBean.getPossiveisGerentes().get(index));
		}

		if (gerentesSelecionados.size() != 0) {
			if (projetoBean.getGerentes() == null) {
				projetoBean.setGerentes(gerentesSelecionados);
			} else {
				projetoBean.getGerentes().addAll(gerentesSelecionados);
			}

			projetoBean.setSelecaoGerentes(null);

			List<Recurso> possiveisGerentes = recursoDaoImpl.consultarGerentes();

			if (projetoBean.getGerentes() != null) {
				for (Recurso recurso : projetoBean.getGerentes()) {
					possiveisGerentes.remove(recurso);
				}
			}

			projetoBean.setPossiveisGerentes(possiveisGerentes);
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("projeto.gerente");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void selecionarRecursos(ActionEvent event) throws Exception {
		Iterator iterator = projetoBean.getSelecaoRecursos().getKeys();

		List<Recurso> participantesSelecionados = new ArrayList();

		boolean selecionouPapel = true;

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			if (Util.isEmpty(projetoBean.getPossiveisRecursos().get(index).getPapelProjeto())) {
				selecionouPapel = false;
			}

			participantesSelecionados.add(projetoBean.getPossiveisRecursos().get(index));
		}

		if (participantesSelecionados.size() != 0) {
			if (selecionouPapel) {
				if (projetoBean.getRecursos() == null) {
					projetoBean.setRecursos(new ArrayList<RecursoProjeto>());
				}

				List<RecursoProjeto> recursosSelecionados = new ArrayList<RecursoProjeto>();

				List<Papel> papeis = papelDaoImpl.findAll(Papel.class);

				for (Recurso recurso : participantesSelecionados) {
					RecursoProjeto recursoProjeto = new RecursoProjeto();
					RecursoProjetoPK recursoProjetoPK = new RecursoProjetoPK();

					recursoProjetoPK.setRecurso(recurso);
					recursoProjeto.setPapel(selecionarPapel(recurso.getPapelProjeto(), papeis));
					recursoProjeto.setRecursoProjetoPK(recursoProjetoPK);
					recursosSelecionados.add(recursoProjeto);
				}

				projetoBean.getRecursos().addAll(recursosSelecionados);

				projetoBean.setSelecaoRecursos(null);

				List<Recurso> possiveisRecursos = recursoDaoImpl.consultarHabilitados();

				if (projetoBean.getRecursos() != null) {
					for (RecursoProjeto recursoProjeto : projetoBean.getRecursos()) {
						possiveisRecursos.remove(recursoProjeto.getRecursoProjetoPK().getRecurso());
					}
				}

				projetoBean.setPossiveisRecursos(possiveisRecursos);
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", FacesContext.getCurrentInstance().getApplication().getResourceBundle(
								FacesContext.getCurrentInstance(), "messages").getString("mensagem.validacao.projeto.selecionePapel")));
			}
		} else {
			ResourceBundle mensagens = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

			String mensagem = mensagens.getString("mensagem.validacao.selecaoObrigatoria");
			String param1 = labels.getString("projeto.recurso");

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, MessageFormat.format(mensagem, param1)));
		}
	}

	public void mudarAba(ValueChangeEvent event) throws Exception {
		// HtmlTabPanel panel = (HtmlTabPanel) event.getSource();
		// UIInput input = (UIInput)panel.findComponent("nome");
		// if(input.getSubmittedValue() != null){
		// projetoBean.setNome(input.getSubmittedValue().toString());
		// }

		if (event.getNewValue().toString().equals("tabObservadores")) {
			List<Recurso> possiveisObservadores = recursoDaoImpl.consultarObservadores();

			if (projetoBean.getObservadores() != null) {
				for (Recurso recurso : projetoBean.getObservadores()) {
					possiveisObservadores.remove(recurso);
				}
			}

			projetoBean.setPossiveisObservadores(possiveisObservadores);
		} else if (event.getNewValue().toString().equals("tabGerentes")) {
			List<Recurso> possiveisGerentes = recursoDaoImpl.consultarGerentes();

			if (projetoBean.getGerentes() != null) {
				for (Recurso recurso : projetoBean.getGerentes()) {
					possiveisGerentes.remove(recurso);
				}
			}

			projetoBean.setPossiveisGerentes(possiveisGerentes);
		} else if (event.getNewValue().toString().equals("tabRecursos")) {
			List<Recurso> possiveisParticipantes = recursoDaoImpl.consultarHabilitados();

			if (projetoBean.getRecursos() != null) {
				for (RecursoProjeto recursoProjeto : projetoBean.getRecursos()) {
					possiveisParticipantes.remove(recursoProjeto.getRecursoProjetoPK().getRecurso());
				}
			}

			projetoBean.setPossiveisRecursos(possiveisParticipantes);
		}
	}

	@Transactional
	public void showDetails() throws Exception {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		Projeto projeto = projetoDaoImpl.find(Projeto.class, projetoBean.getIdProjeto());

		projetoBean.setNome(projeto.getNome());
		projetoBean.setDescricao(projeto.getDescricao());
		projetoBean.setDataInicio(projeto.getDataInicio());
		projetoBean.setDataInicioPrevista(projeto.getDataInicioPrevista());
		projetoBean.setDataFim(projeto.getDataFimPrevista());
		projetoBean.setDataFimPrevista(projeto.getDataFimPrevista());
		projetoBean.setStatus(projeto.getStatus());
		projetoBean.setCriador(projeto.getCriador());

		List<Recurso> gerentes = new ArrayList<Recurso>();
		gerentes.addAll(projeto.getGerentes());
		projetoBean.setGerentes(gerentes);

		List<RecursoProjeto> recursos = new ArrayList<RecursoProjeto>();
		recursos.addAll(projeto.getRecursos());
		projetoBean.setRecursos(recursos);

		List<Recurso> observadores = new ArrayList<Recurso>();
		observadores.addAll(projeto.getObservadores());
		projetoBean.setObservadores(observadores);

		projetoBean.setDetailState();

		projetoBean.getBreadCrumb().remove(projetoBean.getBreadCrumb().size() - 1);
		projetoBean.getBreadCrumb().add(labels.getString("breadCrumb.projeto.detail"));

		projetoBean.setPageMessage(labels.getString("info.paginaDetalhe"));
	}

	public void backToList(ActionEvent event) {
		ResourceBundle labels = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");

		projetoBean.clear();
		projetoBean.setListState();
		projetoBean.setProjetos(projetoDaoImpl.findAll(Projeto.class));
		projetoBean.setPageMessage(labels.getString("info.paginaLista"));
	}

	public List<SelectItem> getPapeis() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Recurso recurso = (Recurso) request.getAttribute("item");

		for (Papel papel : recurso.getPapeis()) {
			itens.add(new SelectItem(papel.getNome(), papel.getNome()));
		}

		return itens;
	}

	private Papel selecionarPapel(String nomePapel, List<Papel> papeis) {
		for (Papel papel : papeis) {
			if (papel.getNome().equalsIgnoreCase(nomePapel)) {
				return papel;
			}
		}

		return null;
	}

	/**
	 * @return the projetoDaoImpl
	 */
	public ProjetoDao getProjetoDaoImpl() {
		return projetoDaoImpl;
	}

	/**
	 * @param projetoDaoImpl
	 *            the projetoDaoImpl to set
	 */
	public void setProjetoDaoImpl(ProjetoDao projetoDaoImpl) {
		this.projetoDaoImpl = projetoDaoImpl;
	}

	/**
	 * @return the projetoBean
	 */
	public ProjetoBean getProjetoBean() {
		return projetoBean;
	}

	/**
	 * @param projetoBean
	 *            the projetoBean to set
	 */
	public void setProjetoBean(ProjetoBean projetoBean) {
		this.projetoBean = projetoBean;
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