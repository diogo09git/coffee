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

@SuppressWarnings("serial")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item implements Serializable{
	
	@Id
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTorra() {
		return torra;
	}

	public void setTorra(String torra) {
		this.torra = torra;
	}

	public String getQnt() {
		return qnt;
	}

	public void setQnt(String qnt) {
		this.qnt = qnt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
}
