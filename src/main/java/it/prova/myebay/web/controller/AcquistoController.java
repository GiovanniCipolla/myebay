package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.service.AcquistoService;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	
	@Autowired
	private AcquistoService acquistoService;
	
	@PostMapping("/compra")
	public String compra(Long idAnnuncio, ModelMap model) {
		
		
		acquistoService.inserisciNuovoAcquisto(idAnnuncio);
		
		return "redirect:/annuncio";
	}

}
