package com.jopss.apostas.web;

import com.jopss.apostas.web.util.ApostasController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador para acessos a URL genericas do sistema.
 */
@Controller
public class GenericController extends ApostasController {
	
	//-------------------------------------------------------
	// ACESSOS PUBLICOS
	//-------------------------------------------------------
	@RequestMapping(value = "/principal/", method = RequestMethod.GET)
	public String abrir() {
		return "template";
	}
}
