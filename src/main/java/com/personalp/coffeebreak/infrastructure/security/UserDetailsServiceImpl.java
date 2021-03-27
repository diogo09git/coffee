package com.personalp.coffeebreak.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personalp.coffeebreak.domain.usuario.Usuario;
import com.personalp.coffeebreak.domain.usuario.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByEmail(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new LoggedUser(usuario);
	}
}
