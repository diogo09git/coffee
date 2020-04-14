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
import com.personalp.coffeebreak.domain.usuario.UsuarioRepository;
import com.personalp.coffeebreak.util.SecurityUtils;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping(path ="/edit")
	public String editUser(Model model) {

		Usuario usuario = usuarioRepository.findById(SecurityUtils.loggedUsuario().getId()).orElseThrow();
		
		model.addAttribute("usuario", usuario);
		ControllerHelper.setEditMode(model, true);
		
		return "cadastro-user";
	}
	
	@PostMapping(path = "/save")
	public String save(@ModelAttribute("usuario") @Valid Usuario usuario,
			Errors errors, Model model) {
		
		if(!errors.hasErrors()) {
			try {
				usuarioService.saveUsuario(usuario);
				model.addAttribute("msg", "Alterações realizadas com sucesso");
			} catch (ValidationException e) {
				errors.rejectValue("msg", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, true);
		return "cadastro-user";
	}
	
}
