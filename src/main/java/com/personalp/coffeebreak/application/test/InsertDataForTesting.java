package com.personalp.coffeebreak.application.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.personalp.coffeebreak.domain.item.Item;
import com.personalp.coffeebreak.domain.item.ItemRepository;
import com.personalp.coffeebreak.domain.usuario.Usuario;
import com.personalp.coffeebreak.domain.usuario.UsuarioRepository;
import com.personalp.coffeebreak.util.StringUtils;

@Component
public class InsertDataForTesting {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Usuario[] usuario = usuarios();
		itens(usuario);
	}
	
	public Usuario[] usuarios() {
		
		List<Usuario> usuario = new ArrayList<Usuario>();
		
		Usuario u = new Usuario();
		u.setNome("Diogo");
		u.setEmail("diogo@");
		u.setSobrenome("silva");
		u.setSenha(StringUtils.encrypt("teste"));
		usuario.add(u);
		usuarioRepository.save(u);
		
		u = new Usuario();
		u.setNome("Mariana");
		u.setEmail("mariana@");
		u.setSobrenome("vieira");
		u.setSenha(StringUtils.encrypt("teste"));
		usuario.add(u);
		usuarioRepository.save(u);
		
		Usuario[] array = new Usuario[usuario.size()];
		return usuario.toArray(array);
	}
	
	public Item[] itens(Usuario[] usuario) {
		
		List<Item> item = new ArrayList<Item>();
		
		Item i = new Item();
		i.setTipo("arabica");
		i.setTorra("media");
		i.setQnt("20");
		i.setUsuario(usuario[0]);
		i.setData(LocalDate.now());
		itemRepository.save(i);
		
		i = new Item();
		i.setTipo("moca");
		i.setTorra("clara");
		i.setQnt("25");
		i.setUsuario(usuario[1]);
		i.setData(LocalDate.now());
		itemRepository.save(i);
		
		Item[] array = new Item[item.size()];
		return item.toArray(array);
	}
}
