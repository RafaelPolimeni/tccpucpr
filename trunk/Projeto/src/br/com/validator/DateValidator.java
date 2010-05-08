package br.com.validator;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.sun.faces.util.MessageFactory;

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
public class DateValidator implements Validator, Serializable {

	public static final String VALIDATOR_ID = "dateValidator";
	private String pattern;
	private boolean isInterval;

	public boolean isInterval() {
		return isInterval;
	}

	public void setInterval(Boolean isInterval) {
		this.isInterval = isInterval;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Calendar data = null;
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.setLenient(false);
		dateFormat.applyPattern(pattern);
		String baseDate = getBaseDate(value, dateFormat);
		FacesMessage message = MessageFactory.getMessage("javax.faces.component.UIInput.INVALID", MessageFactory.getLabel(
				context, component));
		try {
			data = Calendar.getInstance();
			data.setTime(dateFormat.parse(baseDate));
			if (data.get(Calendar.YEAR) < 1753) {
				throw new ValidatorException(message);
			}

			if (isInterval) {
				// valida intervalo
				UIInput parent = getParentInputFromHierarchy(component);
				if (parent != null) {
					Object object = parent.getValue();
					String strDate2 = getBaseDate(object, dateFormat);
					if (strDate2 != null) {
						Date data2 = dateFormat.parse(strDate2);
						// verifica se uma data não é menor que a outra
						if (data.getTime().before(data2)) {
							message = MessageFactory.getMessage("br.com.cpqd.wrcmo.web.validator.DateValidator.INVALID_INTERVAL",
									MessageFactory.getLabel(context, parent), MessageFactory.getLabel(context, component));
							throw new ValidatorException(message);
						}
					}
				}
			}
		} catch (ParseException e) {
			throw new ValidatorException(message);
		}
	}

	private UIInput getParentInputFromHierarchy(UIComponent uiComponent) {
		UIComponent input = uiComponent.getParent();

		while (input != null && !(input instanceof UIPanel)) {
			input = input.getParent();
		}

		if (input != null) {
			for (UIComponent component : input.getChildren()) {
				if (component instanceof UIInput) {
					return (UIInput) component;
				}
			}
		}
		return null;
	}

	private String getBaseDate(Object value, DateFormat dateFormat) {
		String baseDate = null;
		if (value instanceof Date) {
			Date d = (Date) value;
			baseDate = dateFormat.format(d);
		} else if (value instanceof String) {
			baseDate = value.toString();
		}
		return baseDate;

	}

}
