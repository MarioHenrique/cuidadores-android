package br.com.softcare.cuidadores.services;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.PropostaDTO;
import br.com.softcare.cuidadores.dto.PropostaResponseDTO;
import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;

/**
 * Created by mario on 26/11/16.
 */
public class PropostaService {

    private RestExecuter restExecuter = new RestExecuter();

    public void adicionarProposta(PropostaDTO proposta,String token) throws BusinessException {
        restExecuter.post(token, API_URLS.API_PROPOSAL,proposta,PropostaDTO.class);
    }

    public List<LinkedHashMap> listaDePropostas(String token) throws BusinessException {
        return restExecuter.getComplexList(token,API_URLS.API_PROPOSAL,PropostaResponseDTO.class);
    }
}
