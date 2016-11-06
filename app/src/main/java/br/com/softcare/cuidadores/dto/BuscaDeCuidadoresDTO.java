package br.com.softcare.cuidadores.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;

public class BuscaDeCuidadoresDTO {

	private String cep;

	private String bairro;

	private String cidade;

	private String estado;

	private String email;

	private String contato;

	private Long pagina;
	
	private String rua;

	private Long elementosPorPagina;

	private Set<Disponibilidade> disponibilidade = new HashSet<>();

	public String disponibilidadeAsString() {
		StringJoiner join = new StringJoiner(",");
		for (Disponibilidade disp : disponibilidade) {
			join.add(disp.toString());
		}
		return join.length()==0?null:join.toString();
	}

	private Set<Periodo> periodo = new HashSet<>();

	public String periodoAsString() {
		StringJoiner join = new StringJoiner(",");
		for (Periodo per : periodo) {
			join.add(per.toString());
		}
		return join.length()==0?null:join.toString();
	}

	public void adicionarPeriodo(Periodo periodo) {
		this.periodo.add(periodo);
	}

	public void adicionarDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade.add(disponibilidade);
	}

	public void limparPeriodo() {
		this.periodo.clear();
	}

	public void limparDisponibilidade() {
		this.disponibilidade.clear();
	}

	public Set<Disponibilidade> getDisponibilidade() {
		return disponibilidade;
	}

	public Set<Periodo> getPeriodo() {
		return periodo;
	}

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

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public Long getElementosPorPagina() {
		return elementosPorPagina;
	}

	public void setElementosPorPagina(Long elementosPorPagina) {
		this.elementosPorPagina = elementosPorPagina;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

}
