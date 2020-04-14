package com.personalp.coffeebreak.domain.item;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.personalp.coffeebreak.domain.usuario.Usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Adicione o tipo do item")
	@Size(max = 20, message = "A descrição é muito grande")
	private String tipo;
	
	@NotBlank(message = "Adicione o tipo de torra")
	@Size(max = 20, message = "A descrição é muito grande")
	private String torra;
	
	@NotBlank(message = "Adicione a quantidade")
	private String qnt;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@NotNull(message = "Adicione uma data")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
}
