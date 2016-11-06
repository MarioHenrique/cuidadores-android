package br.com.softcare.cuidadores.services;

import android.util.Log;

import static br.com.softcare.cuidadores.enuns.API_URLS.API_USER;

import br.com.softcare.cuidadores.client.RestExecuter;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.dto.UsuarioLogin;
import br.com.softcare.cuidadores.enuns.API_URLS;
import br.com.softcare.cuidadores.exceptions.BusinessException;

public class usuarioService {

	private RestExecuter restExecuter = new RestExecuter();

	public Usuario cadastroDeUsuario(UsuarioAlteracao usuario) throws BusinessException {
			return restExecuter.post(API_USER, usuario, Usuario.class);
	}

	public Usuario login(String email, String senha) throws BusinessException {
		UsuarioLogin usuarioLogin = new UsuarioLogin(email,senha);
		return restExecuter.post(API_URLS.API_USER_LOGIN, usuarioLogin, Usuario.class);
	}

	public Usuario atualizar(UsuarioAlteracao usuario, String token) throws BusinessException {
		return restExecuter.put(token,API_URLS.API_USER,usuario,Usuario.class);
	}

}
