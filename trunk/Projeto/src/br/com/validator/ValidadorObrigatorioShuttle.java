package br.com.validator;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadorObrigatorioShuttle implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object fieldValue) throws ValidatorException {
		Object[] value = (Object[]) fieldValue;

		if (((ArrayList) value[1]).size() == 0) {
			ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
			throw new ValidatorException(new FacesMessage(messages.getString("validacao.selecioneUmPapel")));
		}
	}
}
