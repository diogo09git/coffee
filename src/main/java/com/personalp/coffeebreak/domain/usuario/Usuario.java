package com.personalp.coffeebreak.domain.usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.personalp.coffeebreak.domain.item.Item;
import com.personalp.coffeebreak.util.StringUtils;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Preencha o campo e-mail !")
	@Size(max = 40, message = "O e-mail é muito grande")
	private String email;
	
	@NotBlank(message = "Preencha o campo nome")
	@Size(max = 12, message = "O nome é muito grande")
	private String nome;
	
	@NotBlank(message = "Preencha o campo sobrenome")
	@Size(max = 30, message = "O sobrenome é muito grande")
	private String sobrenome;
	
	@NotBlank(message = "Preencha o campo senha")
	@Size(max = 80, message = "A senha é muito grande")
	private String senha;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Item> itens = new HashSet<Item>(0);
	
	public void encryptPassword() {
		this.senha = StringUtils.encrypt(this.senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	
	public Integer getId() {
		return id;
	}
	
}
