package br.com.service;

import java.util.List;

import br.com.model.Autoridade;
import br.com.model.Recurso;

public interface RecursoService {
	List<Recurso> findAll();
	
	List<Autoridade> findAllAuthorities();
}
