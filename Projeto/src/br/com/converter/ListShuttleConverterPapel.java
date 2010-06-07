package br.com.converter;

import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.hibernate.collection.PersistentBag;
import org.richfaces.component.html.HtmlListShuttle;

import br.com.model.Papel;

public class ListShuttleConverterPapel implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		HtmlListShuttle shuttle = (HtmlListShuttle) arg1;
		ArrayList<Papel> papeis = (ArrayList<Papel>) shuttle.getValue();
		ArrayList<Papel> papeisSource = (ArrayList<Papel>) shuttle.getSourceValue();
		PersistentBag papeisTarget = null;
		ArrayList<Papel> targetValue = null;

		try {
			papeisTarget = (PersistentBag) shuttle.getTargetValue();
		} catch (ClassCastException e) {
			targetValue = (ArrayList<Papel>) shuttle.getTargetValue();
		}

		for (Papel papel : papeis) {
			if (papel.toString().equals(arg2))
				return papel;
		}

		for (Papel papel : papeisSource) {
			if (papel.toString().equals(arg2))
				return papel;
		}

		if (papeisTarget != null) {
			for (Object papel : papeisTarget) {
				if (papel.toString().equals(arg2))
					return papel;
			}
		}

		if (targetValue != null) {
			for (Papel papel : targetValue) {
				if (papel.toString().equals(arg2))
					return papel;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
	}
}
