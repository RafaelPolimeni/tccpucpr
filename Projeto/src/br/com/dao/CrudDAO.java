package br.com.dao;

import java.io.Serializable;
import java.util.List;

import br.com.model.EntityInterface;

public interface CrudDAO<T extends EntityInterface> {

	/**
	 * Atualia o objeto
	 * 
	 * @param objeto
	 */
	void save(T objeto);

	/**
	 * Atualiza o objeto
	 * 
	 * @param objeto
	 */
	T update(T objeto);

	/**
	 * deleta o objeto
	 * 
	 * @param objeto
	 */
	void delete(T objeto);

	/**
	 * Cria uma consulta para o objeto do tipo clazz utilizando sua chave
	 * primaria como filtro
	 * 
	 * @param objeto
	 */
	T find(Class<T> clazz, Serializable primaryKey);

	/**
	 * Gera uma consulta com todos os campo do objeto de entrada
	 * 
	 * @param objeto
	 */
	List<T> simpleFilter(T t);

	/**
	 * realiza uma consulta para o objeto de entrada listando todos os registros
	 * do mesmo
	 * 
	 * @param objeto
	 */
	List<T> findAll(Class<T> clazz);
}
