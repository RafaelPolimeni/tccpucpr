package br.com.login;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class PhaseListenerTeste implements PhaseListener {
	private static final long serialVersionUID = -1216620620302322995L;

	@Override
	public void beforePhase(final PhaseEvent arg0) {
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
				AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		if (session.getAttribute("userNotFound") != null) {
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage("User not found!"));
			session.setAttribute("userNotFound", null);
		}
		
		if(e != null){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
					null);
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage("Erro", e.getMessage()));
		}	
	}

	@Override
	public void afterPhase(final PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.INVOKE_APPLICATION;
	}

}