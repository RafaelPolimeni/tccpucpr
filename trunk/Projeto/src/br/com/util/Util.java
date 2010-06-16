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

	/**
	 * Respons�vel por recuperar um objeto da sess�o
	 * 
	 * @param String id - Id do objeto na sess�o
	 * @return Object - objeto na sess�o or null caso n�o haja na sess�o
	 */
	public static Object getFromSession(String id) {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		return sessao.getAttribute(id);
	}

	/**
	 * Respons�vel por verificar se uma String � nula ou vazia
	 * 
	 * @param String str - String
	 * @return boolean - true se for vazia ou nula e false se n�o for
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 ? true : false;
	}

	public static boolean isEmpty(List lista) {
		return lista == null || lista.size() == 0 ? true : false;
	}
}
