package br.com.service;

import br.com.model.Papel;

public interface PapelService {

	String findAll() throws Exception;

	void prepareCreate() throws Exception;

	void confirmCreate() throws Exception;

	void showDetails() throws Exception;

	void prepareUpdate() throws Exception;

	void confirmUpdate() throws Exception;

	void prepareDelete() throws Exception;

	void cancelDelete() throws Exception;

	void delete() throws Exception;

	void backToList() throws Exception;

	void update(Papel role) throws Exception;

	void delete(Papel role) throws Exception;
}
