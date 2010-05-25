package br.com.service;

import java.util.List;

import br.com.model.Perfil;

public interface RecursoService {
	String findAll();
	
	List<Perfil> findAllAuthorities();
}
