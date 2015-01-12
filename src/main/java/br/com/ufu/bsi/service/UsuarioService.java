package br.com.ufu.bsi.service;

import java.util.List;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Usuario;

public interface UsuarioService extends GenericService<Usuario>{

	Usuario findByLoginAndSenha(String login, String senha) throws SiscordGenericException;
	
	Usuario salvar(Usuario usuario) throws SiscordGenericException;
	
	List<Usuario> findByStatusAndTipoUsuario(Character status, Character tipoUsuario) throws SiscordGenericException;
}
