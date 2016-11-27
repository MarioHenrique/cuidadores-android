package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Periodo {
	MANHA("MORNING"),TARDE("AFTERNOON"),NOITE("NIGHT");

	private String periodo;

	private Periodo(String periodo){
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	@Override
	@JsonValue
	public String toString() {
		return periodo;
	}

	public static Periodo getByName(String name){
		for(Periodo periodo: values()){
			if(periodo.getPeriodo().equals(name)){
				return periodo;
			}
		}
		return null;
	}

}
