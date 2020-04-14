package com.personalp.coffeebreak.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping(path = "/login")
	public String login() {
		return "login-user";
	}
	
	@GetMapping(path = "/login-error")
	public String error(Model model) {
		model.addAttribute("msg", "Credenciais inválidas");
		return "login-user";
	}
}
