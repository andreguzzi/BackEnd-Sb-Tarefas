package br.com.tarefas.controller.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

	private Integer id;

	@NotBlank(message = "Campo nome do usuário não pode estar vazio")
	@Column(length = 100, unique = true)
	private String nome;

	@NotBlank(message = "Campo senha do usuário não pode estar vazio")
	@Column(nullable = false)
	private String senha;
	
	private Set<RoleRequest> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<RoleRequest> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleRequest> roles) {
		this.roles = roles;
	}

}
