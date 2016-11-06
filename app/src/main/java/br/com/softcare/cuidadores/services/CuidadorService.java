package br.com.softcare.cuidadores.services;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
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

}
