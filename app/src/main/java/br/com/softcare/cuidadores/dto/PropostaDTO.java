package br.com.softcare.cuidadores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;

/**
 * Created by mario on 26/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropostaDTO implements Serializable{

    private Long id;

    @JsonProperty("availability")
    private Set<Disponibilidade> disponibilidade = new HashSet<>();

    @JsonProperty("period")
    private Set<Periodo> periodo =  new HashSet<>();

    @JsonProperty("initialDate")
    private String dataInicial;

    @JsonProperty("finalDate")
    private String dataFinal;

    @JsonProperty("patientId")
    private Long pacienteId;

    @JsonProperty("careGiverId")
    private Long cuidadorId;

    public void adicionarPeriodo(Periodo periodo){
        this.periodo.add(periodo);
    }

    public void adicionarDisponibilidade(Disponibilidade disponibilidade){
        this.disponibilidade.add(disponibilidade);
    }

    public Set<Disponibilidade> getDisponibilidade() {
        return disponibilidade;
    }

    public Set<Periodo> getPeriodo() {
        return periodo;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getCuidadorId() {
        return cuidadorId;
    }

    public void setCuidadorId(Long cuidadorId) {
        this.cuidadorId = cuidadorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
