package br.com.softcare.cuidadores.services;

import java.util.List;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;

/**
 * Created by mario on 20/11/16.
 */
public class TratamentoService {

    private RestExecuter restExecuter = new RestExecuter();

    public List<TratamentoDTO> listaTratamentos(String token, Long id) throws BusinessException {
        return restExecuter.getList(token, API_URLS.API_PATIENT_ID_TREATMENT,TratamentoDTO.class,id);
    }

    public void deletar(String token, Long pacienteId, Long tratamentoId) throws BusinessException {
        restExecuter.delete(token,API_URLS.API_PATIENT_ID_TREATMENT_ID,pacienteId,tratamentoId);
    }

    public TratamentoDTO atualizar(String token, TratamentoDTO tratamentoDTO, Long pacienteId, Long tratamentoId) throws BusinessException {
        return restExecuter.put(token,API_URLS.API_PATIENT_ID_TREATMENT_ID,tratamentoDTO,TratamentoDTO.class,pacienteId,tratamentoId);
    }

    public TratamentoDTO criar(String token, TratamentoDTO tratamentoDTO, Long pacienteId) throws BusinessException {
        return restExecuter.post(token,API_URLS.API_PATIENT_ID_TREATMENT,tratamentoDTO,TratamentoDTO.class,pacienteId);
    }
}
