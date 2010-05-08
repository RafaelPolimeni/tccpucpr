package br.com.tags;

import javax.faces.validator.Validator;

import br.com.validator.DateValidator;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.jsf.ValidateHandler;
import com.sun.facelets.tag.jsf.ValidatorConfig;

/**
 * Classe que representa a tag do validador de data
 * 
 * @author LINT
 * @since 02/12/2008
 * @version 1.0
 */
public class DateValidatorTag extends ValidateHandler {

	private TagAttribute pattern;
	private TagAttribute isInterval;

	public DateValidatorTag(ValidatorConfig config) {
		super(config);
		pattern = getRequiredAttribute("pattern");
		isInterval = getAttribute("isInterval");
	}

	protected Validator createValidator(FaceletContext ctx) {
		return ctx.getFacesContext().getApplication().createValidator(DateValidator.VALIDATOR_ID);
	}

	@Override
	protected void setAttributes(FaceletContext ctx, Object instance) {
		super.setAttributes(ctx, instance);
		DateValidator dateValidator = DateValidator.class.cast(instance);
		dateValidator.setPattern(pattern.getValue());
		if (isInterval != null)
			dateValidator.setInterval(new Boolean(isInterval.getValue()));
	}
}