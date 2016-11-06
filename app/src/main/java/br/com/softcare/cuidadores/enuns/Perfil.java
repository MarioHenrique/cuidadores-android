package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Perfil {

	RESPONSAVEL("RESPONSABLE"),CUIDADOR("CAREGIVER");

	private String perfil;

	private Perfil(String perfil){
		this.perfil = perfil;
	}

	@Override
	@JsonValue
	public String toString() {
		return perfil;
	}
	
}
