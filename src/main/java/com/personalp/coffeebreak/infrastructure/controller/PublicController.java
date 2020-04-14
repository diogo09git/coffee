package com.personalp.coffeebreak.infrastructure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personalp.coffeebreak.application.service.UsuarioService;
import com.personalp.coffeebreak.application.service.ValidationException;
import com.personalp.coffeebreak.domain.usuario.Usuario;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(path = "/home")
	public String viewHome() {
		return "home-system";
	}
	
	@GetMapping(path = "/usuario/new")
	public String newUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		ControllerHelper.setEditMode(model, false);
		return "cadastro-user";
	}
	
	@PostMapping(path = "/usuario/save")
	public String saveUsuario(@ModelAttribute("usuario") @Valid Usuario usuario,
			Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			
			try {
				usuarioService.saveUsuario(usuario);
				model.addAttribute("msg", "Usuário cadastrado com sucesso !");
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}			
		}
		
		ControllerHelper.setEditMode(model, false);
		
		return "cadastro-user";
	}
}
