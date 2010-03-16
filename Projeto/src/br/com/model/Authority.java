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
@Table(name="Authority")
@NamedQueries( {
		@NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a order by a.name")})
public class Authority implements EntityInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4097851825814202918L;

	public Authority() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idAuthority")
	private Integer idAuthority;
	
	@Column(name="name")
	private String name;
	
	@Column(name="displayName")
	private String displayName;
	
	public Serializable getPrimaryKey() {
		return getIdAuthority();
	}
	
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

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}