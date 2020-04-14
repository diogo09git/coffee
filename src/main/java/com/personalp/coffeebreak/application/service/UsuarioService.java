package com.personalp.coffeebreak.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalp.coffeebreak.domain.usuario.Usuario;
import com.personalp.coffeebreak.domain.usuario.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public void saveUsuario(Usuario usuario) throws ValidationException{
		
		if(!validateEmail(usuario.getEmail(), usuario.getId())) {
			throw new ValidationException("O e-mail está duplicado !");
		}
		
		if(usuario.getId() != null) {
			Usuario usuarioDB = usuarioRepository.findById(usuario.getId()).orElseThrow();
			usuario.setSenha(usuarioDB.getSenha());
			
		}else {
			usuario.encryptPassword();
		}
		
		usuarioRepository.save(usuario);
	}
	
	private boolean validateEmail(String email, Integer id) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario != null) {
			
			if(id == null) {
				return false;
			}
			
			if(!usuario.getId().equals(id)) {
				return false;
			}
		}
		
		return true;
	}
}
