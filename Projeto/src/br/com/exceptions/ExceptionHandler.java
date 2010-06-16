package br.com.exceptions;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import com.sun.faces.application.ActionListenerImpl;

/**
 * Classe responsável por tratar erros inesperados
 * 
 * @author Roberto Furutani
 * 
 */
public class ExceptionHandler extends ActionListenerImpl {

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			super.processAction(event);
		} catch (Exception e) {
			if (e.getCause().getCause() instanceof BaseDadosException) {
				context.getApplication().getNavigationHandler().handleNavigation(context, null, "erroBaseDados");
			} else {
				context.getApplication().getNavigationHandler().handleNavigation(context, null, "erro");
			}
		}
	}
}
