package br.com.bean;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.stereotype.Service;

@Service
public abstract class BeanAbstrato {
	// States for CRUD
	private boolean listState;
	private boolean createState;
	private boolean updateState;
	private boolean detailState;
	private boolean showModal;
	
	
	private String pageMessage;
	
	// Bread Crumb
	private List<String> breadCrumb;
	
	public BeanAbstrato() {
		
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

	public boolean isShowModal() {
		return showModal;
	}

	public void setShowModal(boolean showModal) {
		this.showModal = showModal;
	}
}