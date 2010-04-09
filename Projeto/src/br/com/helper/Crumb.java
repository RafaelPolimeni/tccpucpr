package br.com.helper;

public class Crumb {
	private String description;
	
	public Crumb() {
	}
	
	public Crumb(String description){
		this.description = description;
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