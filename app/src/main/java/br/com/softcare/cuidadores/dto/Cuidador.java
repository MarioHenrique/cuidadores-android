package br.com.softcare.cuidadores.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Cuidador implements Serializable {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String nome;

	@JsonProperty("zipCode")
	private String cep;
	
	@JsonProperty("district")
	private String bairro;
	
	@JsonProperty("city")
	private String cidade;
	
	@JsonProperty("state")
	private String estado;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("street")
	private String rua;
	
	@JsonProperty("contact")
	private String contato;

	@JsonProperty("availability")
	private Set<Disponibilidade> disponibilidade = new HashSet<>();
	
	@JsonProperty("period")
	private Set<Periodo> periodo = new HashSet<>();

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cuidador [id=" + id + ", cep=" + cep + ", bairro=" + bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", email=" + email + ", rua=" + rua + ", contato=" + contato + ", disponibilidade="
				+ disponibilidade + ", periodo=" + periodo + "]";
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
