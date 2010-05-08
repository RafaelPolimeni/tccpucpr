package br.com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Role")
@NamedQueries( {
		@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r order by r.name")})
public class Role implements EntityInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3498796718450695727L;

	public Role() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRole", nullable = false)
	private Integer idRole;
	
	@Column(name="name", length=100, nullable=false)
	private String name;
	
	@Column(name="description", length=600, nullable=false)
	private String description;
	
	public Serializable getPrimaryKey() {
		return getIdRole();
	}

	/**
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
