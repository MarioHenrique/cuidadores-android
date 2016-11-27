package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by mario on 26/11/16.
 */

public enum Status {

    PENDING("PENDING","Pendente de aprocação do cuidador"),ACEPTED("ACEPTED","Esperando inicialização por parte do responsavel"),DENIED("DENIED","Contrato negado pelo cuidador"),INITIALIZED("INITIALIZED","Contrato iniciado"),FINISHED("FINISHED","Contrato finalizado"),CANCELED("CANCELED","Contrato cancelado");

    private String status;
    private String descricao;

    private Status(String status, String descricao){
        this.status = status;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }

}
