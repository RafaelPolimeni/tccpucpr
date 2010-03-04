package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authority")
public class Authority {
	public Authority() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idAuthority")
	private Integer idAuthority;
	
	@Column(name="name")
	private String name;

	/**
	 * @return the idAuthority
	 */
	public Integer getIdAuthority() {
		return idAuthority;
	}

	/**
	 * @param idAuthority
	 *            the idAuthority to set
	 */
	public void setIdAuthority(Integer idAuthority) {
		this.idAuthority = idAuthority;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}