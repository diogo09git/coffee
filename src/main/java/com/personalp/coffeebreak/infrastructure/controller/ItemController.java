package com.personalp.coffeebreak.infrastructure.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.personalp.coffeebreak.application.service.ItemService;
import com.personalp.coffeebreak.application.service.ValidationException;
import com.personalp.coffeebreak.domain.item.Item;
import com.personalp.coffeebreak.domain.item.ItemRepository;
import com.personalp.coffeebreak.domain.usuario.Usuario;
import com.personalp.coffeebreak.domain.usuario.UsuarioRepository;
import com.personalp.coffeebreak.util.SecurityUtils;

@Controller
@RequestMapping(path = "/usuario")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping(path = "/home")
	public String home(Model model) {
		
		Usuario usuario = usuarioRepository.findById(SecurityUtils.loggedUsuario().getId()).orElseThrow();
		model.addAttribute("usuario", usuario);
		
		List<Item> itens = itemRepository.findAll();
		model.addAttribute("itens", itens);
		
		model.addAttribute("item", new Item());
		
		ControllerHelper.setEditMode(model, false);
		return "home-usuario";
	}
	
	@PostMapping(path = "/item/save")
	public String save(@ModelAttribute("item") @Valid Item item,
			Errors error, Model model) {
		
		Usuario usuario = usuarioRepository.findById(SecurityUtils.loggedUsuario().getId()).orElseThrow();
		model.addAttribute("usuario", usuario);
		
		if(!error.hasErrors()) {
			try {
				itemService.saveItem(item);
				return "redirect:/usuario/home";
				
			} catch (ValidationException e) {
				error.rejectValue("msg", null, e.getMessage());
			}
		}
		
		List<Item> itens = itemRepository.findAll();
		model.addAttribute("itens", itens);
		
		ControllerHelper.setEditMode(model, false);
		return "home-usuario";
	}
	
	@GetMapping(path = "/item/remove")
	public String removeItem(@RequestParam("itemId") Integer itemId) {
		
		itemRepository.deleteById(itemId);
		
		return "redirect:/usuario/home";
	}
	
	@GetMapping(path = "/item/edit")
	public String editItem(@RequestParam("itemId") Integer itemId, Model model) {
		
		Usuario usuario = usuarioRepository.findById(SecurityUtils.loggedUsuario().getId()).orElseThrow();
		model.addAttribute("usuario", usuario);
		
		List<Item> itens = itemRepository.findAll();
		model.addAttribute("itens", itens);
		
		Item item = itemRepository.findById(itemId).orElseThrow();
		model.addAttribute("item", item);
		
		ControllerHelper.setEditMode(model, true);
		return "home-usuario";
	}
	
	@PostMapping(path = "/edit/save")
	public String editSave(@ModelAttribute("item") @Valid Item item,
			Errors error, Model model) {
		
		Usuario usuario = usuarioRepository.findById(SecurityUtils.loggedUsuario().getId()).orElseThrow();
		model.addAttribute("usuario", usuario);
		
		if(!error.hasErrors()) {
			try {
				itemService.saveItem(item);
				return "redirect:/usuario/home";
				
			} catch (ValidationException e) {
				error.rejectValue("msg", null, e.getMessage());
			}
		}
		return "home-usuario";
	}
}
