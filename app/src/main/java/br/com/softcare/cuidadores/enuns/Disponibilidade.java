package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Disponibilidade {

	SEGUNDA("MONDAY"), TERCA("TUESDAY"), QUARTA("WEDNESDAY"), QUINTA("THURSDAY"), SEXTA("FRIDAY"), SABADO(
			"SATURDAY"), DOMINGO("SUNDAY");

	private String disponibilidade;

	private Disponibilidade(String disponibilidade){
		this.disponibilidade = disponibilidade;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	@Override
	@JsonValue
	public String toString() {
		return disponibilidade;
	}

	public static Disponibilidade getByName(String name){
		for(Disponibilidade disp : values()){
			if(disp.getDisponibilidade().equals(name)){
				return disp;
			}
		}
		return null;
	}

}
