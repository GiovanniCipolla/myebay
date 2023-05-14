package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.service.AcquistoService;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	
	@Autowired
	private AcquistoService acquistoService;
	
	@GetMapping
	public ModelAndView listAllAcquisti() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("acquisto_list_attr",
				AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.miaLista()));
		mv.setViewName("acquisto/list");
		return mv;
	}
	
	
	@PostMapping("/compra")
	public String compra(Long idAnnuncio, ModelMap model) {
	
		//------------------ FARE TRY CATCH DEL METODO ---------------------------
		
		
			acquistoService.inserisciNuovoAcquisto(idAnnuncio);
		
		
		return "redirect:/annuncio";
	}
	
	
	@GetMapping("/myList")
	public String myList(ModelMap model) {
		
		acquistoService.miaLista();
		
		return "acquisto/list";
	}

}
