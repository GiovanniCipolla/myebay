package it.prova.myebay.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/home",""})
	public String home() {
		return "homepageFree";
	}
}
