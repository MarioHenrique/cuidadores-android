package br.com.softcare.cuidadores.services;

import java.util.List;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;

/**
 * Created by mario on 19/11/16.
 */
public class PacienteService {

    private RestExecuter restExecuter = new RestExecuter();

    public List<PacienteDTO> listaDePacientes(String token) throws BusinessException {
        return restExecuter.getList(token, API_URLS.API_PATIENT,PacienteDTO.class);
    }


    public void atualizar(PacienteDTO paciente, String token) throws BusinessException {
        restExecuter.put(token,API_URLS.API_PATIENT_ID,paciente,PacienteDTO.class,paciente.getId());
    }

    public void criar(PacienteDTO paciente, String token) throws BusinessException {
        restExecuter.post(token,API_URLS.API_PATIENT,paciente,PacienteDTO.class);
    }

    public void deletar(PacienteDTO paciente, String token) throws BusinessException {
        restExecuter.delete(token,API_URLS.API_PATIENT_ID,paciente.getId());
    }
}
