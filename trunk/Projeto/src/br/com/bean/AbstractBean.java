package br.com.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.richfaces.component.html.HtmlRichMessages;

import br.com.helper.BreadCrumb;

public class AbstractBean {
	private HtmlRichMessages errorMessage;
	private HtmlRichMessages infoMessage;
	private ResourceBundle labels;
	
	// States for CRUD
	private boolean createState;
	private boolean updateState;
	private boolean detailState;
	private String modalPanelTitle;
	
	// Bream Crumb
	private List<BreadCrumb> breadCrumb;
	
	public AbstractBean() {
		UserBean userBean = (UserBean)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("userBean");
		setLabels(ResourceBundle.getBundle("br.com.messageresource.Labels", new Locale(userBean == null ? "pt_BR" : userBean.getLocale())));
		breadCrumb = new ArrayList<BreadCrumb>();
		BreadCrumb bread1 = new BreadCrumb();
		bread1.setDescription("bread1");
		
		BreadCrumb bread2 = new BreadCrumb();
		bread2.setDescription("bread2");
		
		breadCrumb.add(bread1);
		breadCrumb.add(bread2);
	}
	
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
	 * @return the createState
	 */
	public boolean isCreateState() {
		return createState;
	}

	/**
	 * @param createState the createState to set
	 */
	public void setCreateState(boolean createState) {
		this.createState = createState;
	}

	/**
	 * @return the updateState
	 */
	public boolean isUpdateState() {
		return updateState;
	}

	/**
	 * @param updateState the updateState to set
	 */
	public void setUpdateState(boolean updateState) {
		this.updateState = updateState;
	}

	/**
	 * @return the detailState
	 */
	public boolean isDetailState() {
		return detailState;
	}

	/**
	 * @param detailState the detailState to set
	 */
	public void setDetailState(boolean detailState) {
		this.detailState = detailState;
	}

	/**
	 * @return the modalPanelTitle
	 */
	public String getModalPanelTitle() {
		return modalPanelTitle;
	}

	/**
	 * @param modalPanelTitle the modalPanelTitle to set
	 */
	public void setModalPanelTitle(String modalPanelTitle) {
		this.modalPanelTitle = modalPanelTitle;
	}

	/**
	 * @return the breadCrumb
	 */
	public List<BreadCrumb> getBreadCrumb() {
		return breadCrumb;
	}

	/**
	 * @param breadCrumb the breadCrumb to set
	 */
	public void setBreadCrumb(List<BreadCrumb> breadCrumb) {
		this.breadCrumb = breadCrumb;
	}
}