package br.com.dao;

import java.util.List;

import br.com.model.Perfil;
import br.com.model.Recurso;

public interface RecursoDao extends CrudDAO<Recurso> {
	
	List<Perfil> findAllPerfis();
	
	List<Recurso> consultarObservadores();
	
	List<Recurso> consultarGerentes();
	
	List<Recurso> consultarHabilitados();
}
