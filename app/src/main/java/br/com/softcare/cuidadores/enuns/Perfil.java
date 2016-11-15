package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Perfil {

	RESPONSAVEL("RESPONSABLE","Responsavel"),CUIDADOR("CAREGIVER","Cuidador");

	private String perfil;
	private String label;

	private Perfil(String perfil,String label){
		this.perfil = perfil;
		this.label = label;
	}

	@Override
	@JsonValue
	public String toString() {
		return perfil;
	}

	public String getLabel() {
		return label;
	}
}
