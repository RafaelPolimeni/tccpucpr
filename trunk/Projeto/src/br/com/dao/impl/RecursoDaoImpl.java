package br.com.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.dao.RecursoDao;
import br.com.model.Perfil;
import br.com.model.Recurso;

@Repository
public class RecursoDaoImpl extends CrudDAOImpl<Recurso> implements RecursoDao {

	@PersistenceContext
	EntityManager entityManager;

	public List<Perfil> findAllPerfis() {
		return entityManager.createNamedQuery(Perfil.class.getSimpleName() + ".findAll").getResultList();
	}

	public List<Recurso> consultarObservadores() {
		return entityManager.createNamedQuery("Recurso.observadores").getResultList();
	}
	
	public List<Recurso> consultarGerentes() {
		return entityManager.createNamedQuery("Recurso.gerentes").getResultList();
	}
	
	public List<Recurso> consultarHabilitados() {
		return entityManager.createNamedQuery("Recurso.habilitados").getResultList();
	}
}