package br.com.softcare.cuidadores.services;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.PropostaDTO;
import br.com.softcare.cuidadores.dto.PropostaResponseDTO;
import br.com.softcare.cuidadores.dto.PropostaStatusDTO;
import br.com.softcare.cuidadores.dto.SintomaDTO;
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

    public void alterarStatus(String token, Long id, String status) throws BusinessException {
        final PropostaStatusDTO proposta = new PropostaStatusDTO();
        proposta.setId(id);
        proposta.setStatus(status);
        restExecuter.put(token,API_URLS.API_PROPOSAL_STATUS,proposta,String.class);
    }

    public void adicionarSintoma(String token, Long contratoId, String descricaoSintoma) throws BusinessException {
        final SintomaDTO sintomaDTO = new SintomaDTO();
        sintomaDTO.setDescricao(descricaoSintoma);
        restExecuter.post(token,API_URLS.API_PROPOSAL_ID_SYMPTOM,sintomaDTO,SintomaDTO.class,contratoId);
    }

    public List<SintomaDTO> listaDeSintomas(String token, Long contratoId) throws BusinessException {
        return restExecuter.getList(token,API_URLS.API_PROPOSAL_ID_SYMPTOM,SintomaDTO.class,contratoId);
    }

    public void atualizarSintoma(String token, Long contratoId, SintomaDTO sintoma) throws BusinessException {
        restExecuter.put(token,API_URLS.API_PROPOSAL_ID_SYMPTOM_ID,sintoma,SintomaDTO.class,contratoId,sintoma.getId());
    }

    public void deletarSintoma(String token, Long contratoId, Long id) throws BusinessException {
        restExecuter.delete(token,API_URLS.API_PROPOSAL_ID_SYMPTOM_ID,contratoId,id);
    }
}
