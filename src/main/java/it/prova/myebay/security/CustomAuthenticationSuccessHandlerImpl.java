package it.prova.myebay.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import it.prova.myebay.dto.UtenteDTO;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.utente.UtenteRepository;

@Component
public class CustomAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	

	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
//		HttpSession session = request.getSession(false);
//
//		if (session != null) {
//			// memorizziamo le informazioni dell'utente nella sessione
//			
//			Utente utenteFromDb = utenteRepository.findByUsername(authentication.getName()).orElseThrow(
//					() -> new UsernameNotFoundException("Username " + authentication.getName() + " not found"));
//			
//			UtenteDTO utenteParziale = new UtenteDTO();
//			utenteParziale.setNome(utenteFromDb.getNome());
//			utenteParziale.setCognome(utenteFromDb.getCognome());
//			utenteParziale.setUsername(utenteFromDb.getUsername());
//			utenteParziale.setCreditoResiduo(utenteFromDb.getCreditoResiduo());
//			session.setAttribute("userInfo", utenteParziale);
//
//			String redirectUrl = (String) session.getAttribute("url_prior_login");
//			if (redirectUrl != null) {
//				// rimuoviamo l'URL di reindirizzamento dalla sessione
//				session.removeAttribute("url_prior_login");
//
//				// reindirizziamo l'utente all'URL memorizzato
//				response.sendRedirect(redirectUrl);
//				return;
//			}
//		}
//
//		// reindirizziamo l'utente a una pagina predefinita
//		response.sendRedirect("/home");
		
		//voglio mettere in sessione uno userInfo perché spring security mette solo un principal da cui attingere username
		Utente utenteFromDb = utenteRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Username " + authentication.getName() + " not found"));
		UtenteDTO utenteParziale = new UtenteDTO();
		utenteParziale.setNome(utenteFromDb.getNome());
		utenteParziale.setCognome(utenteFromDb.getCognome());
		utenteParziale.setUsername(utenteFromDb.getUsername());
		utenteParziale.setCreditoResiduo(utenteFromDb.getCreditoResiduo());
		request.getSession().setAttribute("userInfo", utenteParziale);
		response.sendRedirect("home");
		
		SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest != null) {
		String savedRequestUrl = savedRequest.getRequestURI();
		response.sendRedirect(savedRequestUrl);
		return;
		}
		
		// SavedRequest savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
		//
//		         request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", savedRequest);
//		         String savedRequestUrl = savedRequest.getRedirectUrl();
		// response.sendRedirect(savedRequestUrl);
		// return;
		// response.sendRedirect(request.getHeader("referer"));
		// response.sendRedirect(HttpHeaders.REFERER);

		response.sendRedirect("secured/home");

	}

}
