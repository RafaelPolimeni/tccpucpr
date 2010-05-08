package br.com.validator;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator para checar a entrada de uma data.<BR/>
 * 
 * <pre>
 * Deve implementar
 * Serializable ou StateHolder para manter o estado entre requisições
 * </pre>
 * 
 * @author LINT
 * @since 02/12/2008
 * @version 1.0
 */
@SuppressWarnings("serial")
public class EqualValidator implements Validator, Serializable {

	public static final String VALIDATOR_ID = "equalValidator";
	private String attribute;

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String attribute = (String) value;
		
		if(attribute.equals(this.attribute))
			throw new ValidatorException(new FacesMessage("As Strings são iguais"));
	}
}