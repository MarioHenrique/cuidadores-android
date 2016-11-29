package br.com.softcare.cuidadores.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mario on 28/11/16.
 */

public class TratamentosTransferDTO implements Serializable{

    private List<TratamentoDTO> tratamentos;

    public List<TratamentoDTO> getTratamentos() {
        return tratamentos;
    }

    public void setTratamentos(List<TratamentoDTO> tratamentos) {
        this.tratamentos = tratamentos;
    }
}
