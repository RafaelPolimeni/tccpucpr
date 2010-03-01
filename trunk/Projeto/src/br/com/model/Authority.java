package br.com.model;

public class Authority {

	public Authority() {
		
	}

	private Integer idAuthority;
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
