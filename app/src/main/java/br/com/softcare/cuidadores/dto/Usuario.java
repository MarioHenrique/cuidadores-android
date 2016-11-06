package br.com.softcare.cuidadores.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Usuario {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String nome;

	@JsonProperty("email")
	private String email;

	@JsonProperty("contact")
	private String contato;

	@JsonProperty("password")
	private String senha;

	@JsonProperty("token")
	private String token;

	@JsonProperty("zipCode")
	private String cep;
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", contato=" + contato + ", senha=" + senha
				+ ", token=" + token + ", cep=" + cep + ", district=" + district + ", cidade=" + cidade + ", rua=" + rua
				+ ", estado=" + estado + ", perfil=" + perfil + ", disponibilidade=" + disponibilidade + ", periodo="
				+ periodo + "]";
	}

	@JsonProperty("district")
	private String district;
	
	@JsonProperty("city")
	private String cidade;
		
	@JsonProperty("street")
	private String rua;
	
	@JsonProperty("state")
	private String estado;
	
	@JsonProperty("profile")
	private Set<Perfil> perfil;

	@JsonProperty("availability")
	private Set<Disponibilidade> disponibilidade;
	
	@JsonProperty("period")
	private Set<Periodo> periodo;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<Perfil> perfil) {
		this.perfil = perfil;
	}

	public Set<Disponibilidade> getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Set<Disponibilidade> disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Set<Periodo> getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Set<Periodo> periodo) {
		this.periodo = periodo;
	}
	
}
