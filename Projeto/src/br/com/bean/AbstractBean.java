package br.com.bean;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	//
	private String title;
	
	// Bream Crumb
	private List<String> breadCrumb;
	
	public AbstractBean() {
		UserBean userBean = (UserBean)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("userBean");
		setLabels(ResourceBundle.getBundle("br.com.messageresource.Labels", new Locale(userBean == null ? "pt_BR" : userBean.getLocale())));
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
	 * @return the labels
	 */
	public ResourceBundle getLabels() {
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(ResourceBundle labels) {
		this.labels = labels;
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
		getLabels().getString("list");
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
		getLabels().getString("create");
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
		getLabels().getString("update");
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
		getLabels().getString("detail");
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}