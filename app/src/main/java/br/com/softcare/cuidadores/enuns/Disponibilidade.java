package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Disponibilidade {

	SEGUNDA("MONDAY"), TERCA("TUESDAY"), QUARTA("WEDNESDAY"), QUINTA("THURSDAY"), SEXTA("FRIDAY"), SABADO(
			"SATURDAY"), DOMINGO("SUNDAY");

	private String disponibilidade;

	private Disponibilidade(String disponibilidade){
		this.disponibilidade = disponibilidade;
	}
	
	@Override
	@JsonValue
	public String toString() {
		return disponibilidade;
	}
	
}
