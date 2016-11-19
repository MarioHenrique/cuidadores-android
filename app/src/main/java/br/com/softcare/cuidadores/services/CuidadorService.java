package br.com.softcare.cuidadores.services;

import java.util.List;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;

public class CuidadorService {

	private RestExecuter restExecuter = new RestExecuter();

	public ListaDeCuidadores buscaDeCuidadores(BuscaDeCuidadoresDTO busca, String token) throws BusinessException {
		return restExecuter.get(token, API_URLS.API_CAREGIVE, ListaDeCuidadores.class, busca.getCep(),
				busca.getBairro(), busca.getCidade(), busca.getRua(), busca.getEstado(),
				busca.getEmail(),busca.getContato(),busca.disponibilidadeAsString(),busca.periodoAsString(),busca.getPagina(),busca.getElementosPorPagina());
	}

	public EspecialidadeDTO criacaoDeEspecialidade(EspecialidadeDTO especialidade,String token) throws BusinessException {
			return restExecuter.post(token,API_URLS.API_CAREGIVE_SPECIALTY,especialidade,EspecialidadeDTO.class);
	}

	public EspecialidadeDTO atualizarEspecialidade(EspecialidadeDTO especialidadeDTO, String token) throws BusinessException {
		return restExecuter.put(token,API_URLS.API_CAREGIVE_SPECIALTY_ID,especialidadeDTO,EspecialidadeDTO.class,especialidadeDTO.getId());
	}

	public void removerEspecialidade(EspecialidadeDTO especialidadeDTO,String token) throws BusinessException {
		restExecuter.delete(token,API_URLS.API_CAREGIVE_SPECIALTY_ID,especialidadeDTO.getId());
	}

	public List<EspecialidadeDTO> listarEspecialidades(String token) throws BusinessException {
		return restExecuter.getList(token,API_URLS.API_CAREGIVE_SPECIALTY,EspecialidadeDTO.class);
	}
}
