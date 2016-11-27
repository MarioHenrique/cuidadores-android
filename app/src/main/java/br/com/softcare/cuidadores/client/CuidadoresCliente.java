package br.com.softcare.cuidadores.client;

import android.app.ProgressDialog;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.PropostaDTO;
import br.com.softcare.cuidadores.dto.PropostaResponseDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import br.com.softcare.cuidadores.services.CuidadorService;
import br.com.softcare.cuidadores.services.PacienteService;
import br.com.softcare.cuidadores.services.PropostaService;
import br.com.softcare.cuidadores.services.TratamentoService;
import br.com.softcare.cuidadores.services.usuarioService;

public final class CuidadoresCliente {

	private usuarioService userService = new usuarioService();
	private CuidadorService cuidadoreService = new CuidadorService();
	private PacienteService pacienteService = new PacienteService();
	private TratamentoService tratamentoService = new TratamentoService();
	private PropostaService propostaService = new PropostaService();

	private ProgressDialog p;
	private String token = null;
	
	/**
	 * 
	 * Realiza o cadastro de um usuario no sistema, todo o erro que acontecer no sistema 
	 * será retornado como uma BusinessException
	 * 
	 * @param usuario
	 * @return usuario
	 * @throws BusinessException
	 */
	public Usuario cadastrarUsuario(UsuarioAlteracao usuario) throws BusinessException{
		return userService.cadastroDeUsuario(usuario);
	}
	
	/**
	 * 
	 * Faz o login do usuario a partir de um email e senha passada
	 * 
	 * @param email
	 * @param senha
	 * @return
	 * @throws BusinessException
	 */
	
	public Usuario login(String email,String senha) throws BusinessException{
		Usuario usuario = userService.login(email,senha);
		token = usuario.getToken();
		return usuario;
	}
	
	/**
	 * Atualiza as informações de um usuario
	 * @param usuario
	 * @return
	 * @throws BusinessException
	 */
	public Usuario atualizarUsuario(UsuarioAlteracao usuario) throws BusinessException{
		validarToken();
		return userService.atualizar(usuario,token);
	}

	private void validarToken() throws BusinessException {
		if(token == null ){
			throw new BusinessException("Usuario não logado");
		}
	}

	/**
	 * Busca de cuidadores
	 * @param buscaDeCuidadoresDTO
	 * @return
	 * @throws BusinessException
	 */
	public ListaDeCuidadores buscaDeCuidadores(BuscaDeCuidadoresDTO buscaDeCuidadoresDTO) throws BusinessException{
		return cuidadoreService.buscaDeCuidadores(buscaDeCuidadoresDTO,token);
	}

	/**
	 * 	Metodo responsavel pela criação de uma especialidade
	 * @return Especialidade criada com seu id
     */
	public EspecialidadeDTO cadastroDeEspecialidade(EspecialidadeDTO especialidadeDTO) throws BusinessException {
		validarToken();
		return cuidadoreService.criacaoDeEspecialidade(especialidadeDTO,token);
	}

	/**
	 *  Realiza a alteração da especialidade do cuidador logado na aplicação
	 * @param especialidadeDTO especialidade a ser alterada
	 * @return
	 * @throws BusinessException
     */
	public EspecialidadeDTO atualizarEspecialidade(EspecialidadeDTO especialidadeDTO) throws BusinessException {
		validarToken();
		return cuidadoreService.atualizarEspecialidade(especialidadeDTO,token);
	}

	/**
	 * Remove uma especialidade de um cuidador
	 * @param especialidadeDTO
	 * @throws BusinessException
     */
	public void removeEspecialidade(EspecialidadeDTO especialidadeDTO) throws BusinessException {
		 cuidadoreService.removerEspecialidade(especialidadeDTO,token);
	}

	/**
	 *  Lista as especialidades de um cuidador
	 * @return
	 * @throws BusinessException
     */
	public List<EspecialidadeDTO> listarEspecialidades() throws BusinessException {
		validarToken();
		return cuidadoreService.listarEspecialidades(token);
	}


	/**
	 * Lista os pacientes do usuario logado
	 * @return
	 * @throws BusinessException
     */
	public List<PacienteDTO> listaDePacientes() throws BusinessException {
		validarToken();
		return pacienteService.listaDePacientes(token);
	}

	/**
	 * Atualiza os dados de um paciente
	 * @param paciente
     */
	public void atualizarPaciente(PacienteDTO paciente) throws BusinessException {
		validarToken();
		pacienteService.atualizar(paciente,token);
	}

	public void criarPaciente(PacienteDTO paciente) throws BusinessException {
		validarToken();
		pacienteService.criar(paciente,token);
	}

	public void deletarPaciente(PacienteDTO paciente) throws BusinessException {
		validarToken();
		pacienteService.deletar(paciente,token);
	}

	public List<TratamentoDTO> listaTratamentos(Long id) throws BusinessException {
		validarToken();
		return tratamentoService.listaTratamentos(token,id);
	}

	public void deletarTratamento(Long pacienteId, Long tratamentoId) throws BusinessException {
		validarToken();
		tratamentoService.deletar(token,pacienteId,tratamentoId);
	}

	public TratamentoDTO atualizarTratamento(TratamentoDTO tratamentoDTO, Long pacienteId, Long tratamentoId) throws BusinessException {
		validarToken();
		return tratamentoService.atualizar(token,tratamentoDTO,pacienteId,tratamentoId);
	}

	public TratamentoDTO criarTratamento(TratamentoDTO tratamentoDTO, Long id) throws BusinessException {
		validarToken();
		return tratamentoService.criar(token,tratamentoDTO,id);
	}

	public void adicionarProposta(PropostaDTO proposta) throws BusinessException {
		validarToken();
		propostaService.adicionarProposta(proposta,token);
	}

	public List<LinkedHashMap> listaDeContratos() throws BusinessException {
		validarToken();
		return propostaService.listaDePropostas(token);
	}
}
