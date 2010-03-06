package br.com.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.dao.CrudDAO;
import br.com.dao.helper.Parameter;
import br.com.model.EntityInterface;

public class CrudDAOImpl<T extends EntityInterface> implements
		CrudDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

	public CrudDAOImpl() {
	}

	public void delete(T objeto) {
		entityManager.remove(entityManager.find(objeto.getClass(), objeto
				.getPrimaryKey()));
	}
	
	public T find(Class<T> clazz, Serializable primaryKey) {
		T object = (T) entityManager.find(clazz, primaryKey);
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String consulta, Parameter... parametros) {
		Query query = entityManager.createQuery(consulta);
		if (parametros != null)
			for (Parameter hibernateParameter : parametros) {
				query.setParameter(hibernateParameter.getName(),
						hibernateParameter.getParameter());
			}
		List<T> list = query.getResultList();
		return list;
	}

	/**
	 * Método utilizado para listar todas as entidades de um tipo do banco de
	 * dados
	 * 
	 * @param clazz
	 * @param relationEager
	 *            nome dos relacionamentos que devem ser trazidos avidamente
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz) {
		List<T> list = entityManager.createNamedQuery(
				clazz.getSimpleName() + ".findAll").getResultList();
		return list;
	}

	public void save(T objeto) {
		entityManager.persist(objeto);
//		entityManager.flush();
	}

	public T update(T objeto) {
		T retorno = entityManager.merge(objeto);
		return retorno;
	}

	public List<T> simpleFilter(T t) {
//		Example tFilter = Example.create(t).ignoreCase().enableLike(
//				MatchMode.ANYWHERE);
		// List<T> list =
		// entityManager.createCriteria(t.getClass()).add(tFilter).list();
		return null;
	}
}
