package com.personalp.coffeebreak.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personalp.coffeebreak.domain.item.Item;
import com.personalp.coffeebreak.domain.item.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	
	@Transactional
	public void saveItem(Item item) throws ValidationException{
		
		if(item.getUsuario() == null) {
			throw new ValidationException("Não existe usuário logado");
		}
		
		itemRepository.save(item);
	}
	
}
