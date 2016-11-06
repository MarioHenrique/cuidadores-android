package br.com.softcare.cuidadores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioLogin {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String senha;

	public UsuarioLogin(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
