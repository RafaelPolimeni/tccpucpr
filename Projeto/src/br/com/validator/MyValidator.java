package br.com.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

public class MyValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		String string = (String) arg2;
		if(string.equals("RONALDO")){
			FacesMessage message = new FacesMessage();
			message.setSummary("RONALDO");
			
			HttpServletRequest myRequest = (HttpServletRequest) arg0.getExternalContext().getRequest();
	        String teste = myRequest.getParameter("teste");
	        System.out.println(teste);
	        
	        teste = arg1.getAttributes().get("teste").toString();
			throw new ValidatorException(message);
		}
	}
}
