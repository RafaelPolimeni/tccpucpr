package br.com.converter;

import java.util.ArrayList;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.hibernate.collection.PersistentBag;
import org.richfaces.component.html.HtmlListShuttle;

import br.com.model.Recurso;

public class ListShuttleConverterRecurso implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		HtmlListShuttle shuttle = (HtmlListShuttle) arg1;
		ArrayList<Recurso> recursos = (ArrayList<Recurso>) shuttle.getValue();
		ArrayList<Recurso> recursoSource = (ArrayList<Recurso>) shuttle.getSourceValue();
		PersistentBag recursoTarget = null;
		ArrayList<Recurso> targetValue = null;

		try {
			recursoTarget = (PersistentBag) shuttle.getTargetValue();
		} catch (ClassCastException e) {
			targetValue = (ArrayList<Recurso>) shuttle.getTargetValue();
		}

		for (Recurso recurso : recursos) {
			if (recurso.toString().equals(arg2))
				return recurso;
		}

		for (Recurso recurso : recursoSource) {
			if (recurso.toString().equals(arg2))
				return recurso;
		}

		if (recursoTarget != null) {
			for (Object papel : recursoTarget) {
				if (papel.toString().equals(arg2))
					return papel;
			}
		}

		if (targetValue != null) {
			for (Recurso recurso : targetValue) {
				if (recurso.toString().equals(arg2))
					return recurso;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2.toString();
	}
}
