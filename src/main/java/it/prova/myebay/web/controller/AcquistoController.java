package it.prova.myebay.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.AcquistoDTO;
import it.prova.myebay.dto.AnnuncioDTO;
import it.prova.myebay.model.Acquisto;
import it.prova.myebay.service.AcquistoService;
import it.prova.myebay.service.UtenteService;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoService acquistoService;

	@Autowired
	private UtenteService utenteService;

	@GetMapping
	public ModelAndView listAllAcquisti() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("acquisto_list_attr", AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.miaLista()));
		mv.setViewName("acquisto/list");
		return mv;
	}

	@PostMapping("/compra")
	public String compra(Long idAnnuncio, ModelMap model, RedirectAttributes redirectAttrs) {

		// ------------------ FARE TRY CATCH DEL METODO ---------------------------

		if (!utenteService.isAutenticato())
			return "login";

		acquistoService.inserisciNuovoAcquisto(idAnnuncio);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/acquisto";
	}

	@GetMapping("/myList")
	public String myList(ModelMap model) {

		acquistoService.miaLista();

		List<Acquisto> result = acquistoService.miaLista();

		if (result.isEmpty())
			return "home";

		model.addAttribute("acquisto_list_attr",
				AcquistoDTO.createAcquistoDTOListFromModelList(acquistoService.miaLista()));
		return "acquisto/list";
	}

}
