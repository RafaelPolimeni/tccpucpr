package br.com.dao.helper;

import org.hibernate.type.Type;

/**
 * Objeto que representa um parâmetro nomeado do hibernate
 * 
 * @author Renato
 * 
 */
public class Parameter {
	private Object parameter;
	private Type type;
	private String name;

	public Object getParameter() {
		return parameter;
	}

	public Type getType() {
		return type;
	}

	public Parameter(String name, Object parameter, Type type) {
		super();
		this.name = name;
		this.parameter = parameter;
		this.type = type;
	}

	public String getName() {
		return name;
	}
}
