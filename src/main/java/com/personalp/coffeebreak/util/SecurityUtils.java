package com.personalp.coffeebreak.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.personalp.coffeebreak.domain.usuario.Usuario;
import com.personalp.coffeebreak.infrastructure.security.LoggedUser;

public class SecurityUtils {

	public static LoggedUser loggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof AnonymousAuthenticationToken) {
			return null;
		}
		
		return (LoggedUser) authentication.getPrincipal();
	}
	
	
	public static Usuario loggedUsuario() {
		
		LoggedUser loggedUser = loggedUser();
		
		if(loggedUser == null) {
			throw new IllegalStateException("Não existe usuário logado");
		}
		
		return loggedUser.getUsuario();
	}
}
