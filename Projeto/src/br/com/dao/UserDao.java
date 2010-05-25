package br.com.dao;

import br.com.model.Recurso;

public interface UserDao extends CrudDAO<Recurso>{
	Recurso findUserByName(String userName);
}
