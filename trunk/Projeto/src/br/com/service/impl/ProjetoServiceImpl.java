package br.com.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.bean.ProjetoBean;
import br.com.dao.ProjetoDao;
import br.com.dao.RecursoDao;
import br.com.model.Projeto;
import br.com.model.Recurso;
import br.com.service.ProjetoService;

@Service
@Scope(value = "request")
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoDao projetoDaoImpl;

	@Autowired
	private RecursoDao recursoDaoImpl;

	@Autowired
	private ProjetoBean projetoBean;

	public String findAll() {
		projetoBean.setProjetos(projetoDaoImpl.findAll(Projeto.class));

		ResourceBundle labels = projetoBean.getLabels();
		List<String> breadCrumb = new ArrayList<String>();
		breadCrumb.add(labels.getString("breadCrumb.homePage"));
		breadCrumb.add(labels.getString("breadCrumb.maintenance"));
		breadCrumb.add(labels.getString("breadCrumb.projeto.list"));

		projetoBean.setBreadCrumb(breadCrumb);
		projetoBean.setListState();
		projetoBean.setPageMessage(projetoBean.getLabels().getString("info.paginaLista"));

		return "gerenciarProjetos";
	}

	public void prepareCreate() {
		// projetoBean.setPossiveisGerentes(recursoDaoImpl.consultarGerentes());
		// projetoBean.setPossiveisParticipantes(recursoDaoImpl.findAll(Recurso.class));

		projetoBean.clear();
		projetoBean.setCreateState();
		projetoBean.getBreadCrumb().remove(projetoBean.getBreadCrumb().size() - 1);
		projetoBean.getBreadCrumb().add(projetoBean.getLabels().getString("breadCrumb.projeto.create"));
		projetoBean.setPageMessage(projetoBean.getLabels().getString("info.paginaInclusao"));

		// List<SelectItem> itens = new ArrayList<SelectItem>();
		//
		// for (Perfil autoridade : (List<Perfil>) autoridades) {
		// itens.add(new SelectItem(autoridade.getIdAutoridade(),
		// autoridade.getNomeVisivel()));
		// }
		//
		// recursoBean.setAutoridades(itens);
	}

	public void confirmCreate() {
		// try {
		// Projeto projeto = new Projeto();
		// projetoBean.setDataFim(new Date());
		// projetoBean.setDataFimPrevista(new Date());
		// BeanUtils.copyProperties(projeto, projetoBean);
		// projeto.setStatus(1);
		//
		// UserBean userBean = (UserBean) Util.getFromSession("userBean");
		//
		// projeto.setCriador(recursoDaoImpl.find(Recurso.class,
		// userBean.getIdUsuario()));
		//
		// List<Recurso> recursos = projetoBean.getParticipantes();
		// Date data = new Date();
		//
		// List<HistoricoProjeto> historicos = new
		// ArrayList<HistoricoProjeto>();
		// List<RecursoProjeto> alocados = new ArrayList<RecursoProjeto>();
		//
		// for (Recurso recurso : recursos) {
		// HistoricoProjeto historico = new HistoricoProjeto();
		// historico.setProjeto(projeto);
		// historico.setRecurso(recurso);
		// historico.setData(data);
		// historico.setAdicionou(true);
		//
		// historicos.add(historico);
		//
		// RecursoProjetoPK recursoProjetoPK = new RecursoProjetoPK();
		// recursoProjetoPK.setProjeto(projeto);
		// recursoProjetoPK.setRecurso(recurso);
		// RecursoProjeto recursoProjeto = new RecursoProjeto();
		// recursoProjeto.setRecursoProjetoPK(recursoProjetoPK);
		//
		// alocados.add(recursoProjeto);
		// }
		//
		// projeto.setParticipantes(alocados);
		//
		// projetoDaoImpl.incluirProjeto(projeto, historicos);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public void excluirObservador() {
		projetoBean.getObservadores().remove(projetoBean.getRecursoTemp());

		List<Recurso> possiveisObservadores = recursoDaoImpl.consultarObservadores();

		if (projetoBean.getObservadores() != null) {
			for (Recurso recurso : projetoBean.getObservadores()) {
				possiveisObservadores.remove(recurso);
			}
		}

		projetoBean.setPossiveisObservadores(possiveisObservadores);
	}
	
	public void excluirGerente() {
		projetoBean.getGerentes().remove(projetoBean.getRecursoTemp());

		List<Recurso> possiveisGerentes = recursoDaoImpl.consultarGerentes();

		if (projetoBean.getGerentes() != null) {
			for (Recurso recurso : projetoBean.getGerentes()) {
				possiveisGerentes.remove(recurso);
			}
		}

		projetoBean.setPossiveisGerentes(possiveisGerentes);
	}
	
	public void excluirParticipantes() {
		projetoBean.getParticipantes().remove(projetoBean.getRecursoTemp());

		List<Recurso> possiveisParticipantes = recursoDaoImpl.consultarHabilitados();

		if (projetoBean.getParticipantes() != null) {
			for (Recurso recurso : projetoBean.getParticipantes()) {
				possiveisParticipantes.remove(recurso);
			}
		}

		projetoBean.setPossiveisParticipantes(possiveisParticipantes);
	}

	public void selecionarObservadores() {
		Iterator iterator = projetoBean.getSelecaoObservadores().getKeys();

		List<Recurso> recursosSelecionados = new ArrayList();

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			recursosSelecionados.add(projetoBean.getPossiveisObservadores().get(index));
		}

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
	}

	public void selecionarGerentes() {
		Iterator iterator = projetoBean.getSelecaoGerentes().getKeys();

		List<Recurso> gerentesSelecionados = new ArrayList();

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			gerentesSelecionados.add(projetoBean.getPossiveisGerentes().get(index));
		}

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
	}
	
	public void selecionarParticipantes() {
		Iterator iterator = projetoBean.getSelecaoParticipantes().getKeys();

		List<Recurso> participantesSelecionados = new ArrayList();

		while (iterator.hasNext()) {
			Integer index = (Integer) iterator.next();
			participantesSelecionados.add(projetoBean.getPossiveisParticipantes().get(index));
		}

		if (projetoBean.getParticipantes() == null) {
			projetoBean.setParticipantes(participantesSelecionados);
		} else {
			projetoBean.getParticipantes().addAll(participantesSelecionados);
		}

		projetoBean.setSelecaoParticipantes(null);

		List<Recurso> possiveisParticipantes = recursoDaoImpl.consultarHabilitados();

		if (projetoBean.getParticipantes() != null) {
			for (Recurso recurso : projetoBean.getParticipantes()) {
				possiveisParticipantes.remove(recurso);
			}
		}

		projetoBean.setPossiveisParticipantes(possiveisParticipantes);
	}
	
	public void mudarAba(ValueChangeEvent event) {
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
		} else if (event.getNewValue().toString().equals("tabParticipantes")) {
			List<Recurso> possiveisParticipantes = recursoDaoImpl.consultarHabilitados();

			if (projetoBean.getParticipantes() != null) {
				for (Recurso recurso : projetoBean.getParticipantes()) {
					possiveisParticipantes.remove(recurso);
				}
			}

			projetoBean.setPossiveisParticipantes(possiveisParticipantes);			
		}
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
}