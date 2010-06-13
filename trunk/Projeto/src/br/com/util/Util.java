package br.com.util;

import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Util {

	public static UIComponent findComponent(UIComponent parent, String id) {
		if (id.equals(parent.getId())) {
			return parent;
		}
		Iterator<UIComponent> kids = parent.getFacetsAndChildren();
		while (kids.hasNext()) {
			UIComponent kid = kids.next();
			UIComponent found = findComponent(kid, id);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
	
	public static Object getFromSession(String id){
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		return sessao.getAttribute(id);
	}
	
	public static boolean isEmpty(String str){
		return str == null || str.trim().length() == 0 ? true : false;
	}
	
	public static boolean isEmpty(List lista){
		return lista == null || lista.size() == 0 ? true : false;
	}
}
