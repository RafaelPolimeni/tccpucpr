package br.com.bean;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.richfaces.component.html.HtmlRichMessages;

public abstract class AbstractBean {
	private HtmlRichMessages errorMessage;
	private HtmlRichMessages infoMessage;
	private ResourceBundle labels;
	
	// States for CRUD
	private boolean listState;
	private boolean createState;
	private boolean updateState;
	private boolean detailState;
	
	
	private String pageMessage;
	
	// Bread Crumb
	private List<String> breadCrumb;
	
	public AbstractBean() {
		
	}
	
	/**
	 * Responsible for go back to the list state
	 */
	public void backToList(){
		setListState();
		this.findAll();
	}
	
	public abstract String findAll();
	
	/**
	 * @return the errorMessage
	 */
	public HtmlRichMessages getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(HtmlRichMessages errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the infoMessage
	 */
	public HtmlRichMessages getInfoMessage() {
		return infoMessage;
	}
	/**
	 * @param infoMessage the infoMessage to set
	 */
	public void setInfoMessage(HtmlRichMessages infoMessage) {
		this.infoMessage = infoMessage;
	}

	/**
	 * @return the listState
	 */
	public boolean isListState() {
		return listState;
	}

	/**
	 * 
	 */
	public void setListState() {
		this.listState = true;
		this.createState = false;
		this.updateState = false;
		this.detailState = false;
	}

	/**
	 * @return the createState
	 */
	public boolean isCreateState() {
		return createState;
	}

	/**
	 * 
	 */
	public void setCreateState() {
		this.listState = false;
		this.createState = true;
		this.updateState = false;
		this.detailState = false;
	}

	/**
	 * @return the updateState
	 */
	public boolean isUpdateState() {
		return updateState;
	}

	/**
	 * 
	 */
	public void setUpdateState() {
		this.listState = false;
		this.createState = false;
		this.updateState = true;
		this.detailState = false;
	}

	/**
	 * @return the detailState
	 */
	public boolean isDetailState() {
		return detailState;
	}

	/**
	 * 
	 */
	public void setDetailState() {
		this.listState = false;
		this.createState = false;
		this.updateState = false;
		this.detailState = true;
	}

	/**
	 * @return the breadCrumb
	 */
	public List<String> getBreadCrumb() {
		return breadCrumb;
	}

	/**
	 * @param breadCrumb the breadCrumb to set
	 */
	public void setBreadCrumb(List<String> breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

	/**
	 * @return the messages
	 */
	public ResourceBundle getLabels() {
		setLabels(FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels"));
		return labels;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setLabels(ResourceBundle labels) {
		this.labels = labels;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
	}

	/**
	 * @return the pageMessage
	 */
	public String getPageMessage() {
		return pageMessage;
	}

	/**
	 * @param pageMessage the pageMessage to set
	 */
	public void setPageMessage(String pageMessage) {
		this.pageMessage = pageMessage;
	}
}