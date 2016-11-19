package br.com.softcare.cuidadores.client;

import android.app.ProgressDialog;

import java.util.Collections;
import java.util.List;

import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import br.com.softcare.cuidadores.services.CuidadorService;
import br.com.softcare.cuidadores.services.usuarioService;

public final class CuidadoresCliente {

	private usuarioService userService = new usuarioService();
	private CuidadorService cuidadoreService = new CuidadorService();
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
		if(token == null ){
			throw new BusinessException("Usuario não logado");
		}
		return userService.atualizar(usuario,token);
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
		return cuidadoreService.criacaoDeEspecialidade(especialidadeDTO,token);
	}

	/**
	 *  Realiza a alteração da especialidade do cuidador logado na aplicação
	 * @param especialidadeDTO especialidade a ser alterada
	 * @return
	 * @throws BusinessException
     */
	public EspecialidadeDTO atualizarEspecialidade(EspecialidadeDTO especialidadeDTO) throws BusinessException {
		return cuidadoreService.atualizarEspecialidade(especialidadeDTO,token);
	}

	public void removeEspecialidade(EspecialidadeDTO especialidadeDTO) throws BusinessException {
		 cuidadoreService.removerEspecialidade(especialidadeDTO,token);
	}

	public List<EspecialidadeDTO> listarEspecialidades() throws BusinessException {
		return cuidadoreService.listarEspecialidades(token);
	}
}
