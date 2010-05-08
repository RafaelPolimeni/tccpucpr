package br.com.tags;

import javax.faces.validator.Validator;

import br.com.validator.EqualValidator;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.jsf.ValidateHandler;

/**
 * Classe que representa a tag do validador de data
 * 
 * @author LINT
 * @since 02/12/2008
 * @version 1.0
 */
public class EqualTag extends ValidateHandler {
	
	private TagAttribute attribute;

	public EqualTag(TagConfig config) {
		super(config);
		attribute = getRequiredAttribute("attribute");
	}

	protected Validator createValidator(FaceletContext ctx) {
		return ctx.getFacesContext().getApplication().createValidator(EqualValidator.VALIDATOR_ID);
	}

	@Override
	protected void setAttributes(FaceletContext ctx, Object instance) {
		super.setAttributes(ctx, instance);
		EqualValidator equalValidator = EqualValidator.class.cast(instance);
		equalValidator.setAttribute(attribute.getValue());
	}
}