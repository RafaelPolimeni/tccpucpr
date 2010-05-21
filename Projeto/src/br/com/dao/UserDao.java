package br.com.dao;

import br.com.model.Recurso;

public interface UserDao extends CrudDao<Recurso>{
	Recurso findUserByName(String userName);
}
