package br.com.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmptyValidator implements Validator {
	
	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object fieldValue) throws ValidatorException {
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		
		String value = (String) fieldValue;
		if(value == null || value.trim().equals("")){
			throw new ValidatorException(new FacesMessage(messages.getString("validation.empty")));
		}	
	}
}
