package com.personalp.coffeebreak.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByEmail(String email);
	
	
}
