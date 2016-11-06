package br.com.softcare.cuidadores.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;

public class UsuarioAlteracao {

	@JsonProperty("availability")
	private Set<Disponibilidade> disponibilidade = new HashSet<>();

	@JsonProperty("contact")
	private String contato;

	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String nome;

	@JsonProperty("password")
	private String senha;

	@JsonProperty("period")	
	private Set<Periodo> periodo = new HashSet<>();

	@JsonProperty("profile")
	private Set<Perfil> perfil = new HashSet<>();

	@JsonProperty("zipCode")
	private String cep;

	public Set<Disponibilidade> getDisponibilidade() {
		return Collections.unmodifiableSet(disponibilidade);
	}

	public Set<Periodo> getPeriodo() {
		return Collections.unmodifiableSet(periodo);
	}

	public Set<Perfil> getPerfil() {
		return Collections.unmodifiableSet(perfil);
	}

	public void limparPeriodo(){
		this.periodo.clear();
	}
	
	public void limparPerfil(){
		this.perfil.clear();
	}
	
	public void limparDisponibilidade(){
		this.disponibilidade.clear();
	}
	
	public void adicionarPerfil(Perfil perfil){
		this.perfil.add(perfil);
	}
	
	public void adicionarPeriodo(Periodo periodo){
		this.periodo.add(periodo);
	}
	
	public void adicionarDisponibilidade(Disponibilidade disponibilidade){
		this.disponibilidade.add(disponibilidade);
	}
	
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
