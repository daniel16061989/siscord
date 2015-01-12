package br.com.ufu.bsi.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufu.bsi.dao.UsuarioDAO;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.service.UsuarioService;

@Service(value = "usuarioService")
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario> implements UsuarioService{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected JpaRepository<Usuario, Integer> getRepository() {
		return usuarioDAO;
	}

	public Usuario findByLoginAndSenha(String login, String senha) throws SiscordGenericException {
		try {
			return usuarioDAO.findByLoginAndSenha(login, senha);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}

	public List<Usuario> findByStatusAndTipoUsuario(Character status, Character tipoUsuario) throws SiscordGenericException {
		try {
			return usuarioDAO.findByStatusAndTipoUsuario(status, tipoUsuario);
		} catch (Exception e) {
			throw new SiscordGenericException(e.getMessage(), e);
		}
	}
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Transactional(rollbackFor = {Throwable.class})
	@Override
	public Usuario salvar(Usuario usuario) throws SiscordGenericException {
		validarUsuario(usuario);
		return save(usuario);
	}

	private void validarUsuario(Usuario usuario) throws SiscordGenericException{
		if(usuario == null)
			throw new SiscordGenericException("Usu�rio vazio n�o pode ser salvo");
		
		List<String> erros = new ArrayList<String>();
		
		if(!(usuario.getLogin() != null && !usuario.getLogin().trim().equals("")))
			erros.add(" LOGIN");
		if(!(usuario.getSenha() != null && !usuario.getSenha().trim().equals("")))
			erros.add(" SENHA");
		if(!(usuario.getStatus() != null && !usuario.getStatus().equals(' ')))
			erros.add(" STATUS");
		if(!(usuario.getTipoUsuario() != null && !usuario.getTipoUsuario().equals(' ')))
			erros.add(" TIPO USUARIO");
		
		if(!erros.isEmpty())
			throw new SiscordGenericException("Campos obrigat�rios n�o informados [" + erros.toString().replace("[", "(").replace("]", ")") + "]");

		if(usuario.getDtCadastro() == null)
			usuario.setDtCadastro(new Date(System.currentTimeMillis()));
	}
}
