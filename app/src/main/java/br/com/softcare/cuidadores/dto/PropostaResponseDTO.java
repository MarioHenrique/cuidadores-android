package br.com.softcare.cuidadores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.enuns.Status;

/**
 * Created by mario on 26/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropostaResponseDTO implements Serializable{

    private Long id;

    @JsonProperty("propostalInitialDate")
    private String dataInicial;

    @JsonProperty("propostalFinalDate")
    private String propostalFinalDate;

    @JsonProperty("availability")
    private List<Disponibilidade> disponibilidades;

    @JsonProperty("period")
    private List<Periodo> periodos;

    @JsonProperty("patient")
    private PacienteDTO pacientes;

    @JsonProperty("careGiver")
    private Usuario cuidador;

    @JsonProperty("proposalDate")
    private String dataProposta;

    @JsonProperty("status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getPropostalFinalDate() {
        return propostalFinalDate;
    }

    public void setPropostalFinalDate(String propostalFinalDate) {
        this.propostalFinalDate = propostalFinalDate;
    }

    public PacienteDTO getPacientes() {
        return pacientes;
    }

    public void setPacientes(PacienteDTO pacientes) {
        this.pacientes = pacientes;
    }

    public Usuario getCuidador() {
        return cuidador;
    }

    public void setCuidador(Usuario cuidador) {
        this.cuidador = cuidador;
    }

    public String getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(String dataProposta) {
        this.dataProposta = dataProposta;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Disponibilidade> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }
}
