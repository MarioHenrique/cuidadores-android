package br.com.softcare.cuidadores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mario on 19/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EspecialidadeDTO {

    private Long id;

    @JsonProperty(value = "description")
    private String descricao;

    @JsonProperty(value = "name")
    private String nome;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
