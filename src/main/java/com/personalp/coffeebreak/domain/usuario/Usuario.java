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
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
	
	@Id
	@EqualsAndHashCode.Include
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
}
