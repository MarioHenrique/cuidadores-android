package br.com.softcare.cuidadores.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ListaDeCuidadores implements Serializable {

	@JsonProperty("content")
	private List<Cuidador> cuidadores = new ArrayList<>();

	@JsonProperty("number")
	private Long pagina;

	@JsonProperty("numberOfElements")
	private Long totalDeElementosDaPagina;

	@JsonProperty("totalElements")
	private Long totalDeElementos;

	public List<Cuidador> getCuidadores() {
		return cuidadores;
	}

	public void setCuidadores(List<Cuidador> cuidadores) {
		this.cuidadores = cuidadores;
	}

	public Long getPagina() {
		return pagina != null ? pagina + 1 : pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public Long getTotalDeElementosDaPagina() {
		return totalDeElementosDaPagina;
	}

	@Override
	public String toString() {
		return "ListaDeCuidadores [cuidadores=" + cuidadores + ", pagina=" + pagina + ", totalDeElementosDaPagina="
				+ totalDeElementosDaPagina + ", totalDeElementos=" + totalDeElementos + "]";
	}

	public void setTotalDeElementosDaPagina(Long totalDeElementosDaPagina) {
		this.totalDeElementosDaPagina = totalDeElementosDaPagina;
	}

	public Long getTotalDeElementos() {
		return totalDeElementos;
	}

	public void setTotalDeElementos(Long totalDeElementos) {
		this.totalDeElementos = totalDeElementos;
	}

}
