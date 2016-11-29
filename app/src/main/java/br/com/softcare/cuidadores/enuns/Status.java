package br.com.softcare.cuidadores.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by mario on 26/11/16.
 */

public enum Status {

    PENDING("PENDING","Pendente de aprocação do cuidador","PENDENTE"),ACEPTED("ACEPTED","Esperando inicialização por parte do responsavel","ACEITO"),DENIED("DENIED","Contrato negado pelo cuidador","NEGADO"),INITIALIZED("INITIALIZED","Contrato iniciado","INICIADO"),FINISHED("FINISHED","Contrato finalizado pelo responsavel","FINALIZADO"),CANCELED("CANCELED","Contrato cancelado","CANCELADO");

    private String status;
    private String descricao;
    private String portName;

    private Status(String status, String descricao,String portName){
        this.status = status;
        this.descricao = descricao;
        this.portName = portName;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPortName() {
        return portName;
    }

    @Override
    @JsonValue
    public String toString() {
        return status;
    }

}
