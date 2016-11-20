package br.com.softcare.cuidadores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mario on 19/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("contact")
    private String contato;

    @JsonProperty("healthStatus")
    private String estadoDeSaude;

    @JsonProperty("dateOfBirth")
    private String dataNascimento;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEstadoDeSaude() {
        return estadoDeSaude;
    }

    public void setEstadoDeSaude(String estadoDeSaude) {
        this.estadoDeSaude = estadoDeSaude;
    }
}
