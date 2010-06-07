package br.com.service;

import br.com.model.Papel;

public interface PapelService {

	String findAll();

	void prepareCreate();

	void confirmCreate();

	void showDetails();

	void prepareUpdate();

	void confirmUpdate();

	void prepareDelete();

	void cancelDelete();

	void delete();

	void backToList();

	void update(Papel role);

	void delete(Papel role);
}
