package br.com.dao;

import java.util.List;

import br.com.model.Autoridade;
import br.com.model.Recurso;

public interface UsersDao extends CrudDao<Recurso> {
	
	List<Autoridade> findAllAuthorities();
	
}
