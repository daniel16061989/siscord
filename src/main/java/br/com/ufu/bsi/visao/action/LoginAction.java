package br.com.ufu.bsi.visao.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.InformacaoLogin;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.util.EncryptionUtils;

@Namespace(value="/login")
public class LoginAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private String login;
	
	private String senha;
	
	private String novaSenha;

	public static Character TIPO_USUARIO;
	
	private Usuario usuario = new Usuario();

	@Action(value = "index", results = {@Result(name = "success", location="/login.jsp")})
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "fazerLogin", results = {@Result(name = "success", location="/index.jsp"),
			                                 @Result(name = "error", location = "/index.jsp")})
	public String fazerLogin() {
		try {
			if(senha == "") {
				return ERROR;
			}
			
			usuario.setLogin(login);
			usuario.setSenha(senha);
			
//			usuario.setSenha(EncryptionUtils.MD5(usuario.getSenha()));
			
			Usuario usuarioValidado = usuarioService.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
			usuario.setSenha(null);

			if(usuarioValidado != null) {
				if(!(usuarioValidado.getStatus().toString().trim().equals(Usuario.STATUS_ATIVO.toString().trim()) || 
						usuarioValidado.getStatus().toString().trim().equals(Usuario.STATUS_PRIMEIRO_ACESSO.toString().trim()))) {
					return ERROR;
				}
				
				TIPO_USUARIO = usuarioValidado.getTipoUsuario();
				
				// refaz o login
				if(usuarioLogado != null) {
					if(usuarioLogado.getUsuario() != null) {
						this.usuarioLogado.setUsuario(new Usuario());
						this.usuarioLogado.setInformacaoLogin(new InformacaoLogin());
						this.usuarioLogado.setAutenticado(Boolean.FALSE);
					}
				}
				
				HttpSession session = request.getSession();
				sessionConcierge.adicionarUsuarioLogado(usuario.getLogin(), session);

				this.usuarioLogado.setAutenticado(Boolean.TRUE);
				this.usuarioLogado.setUsuario(usuarioValidado);

				InformacaoLogin informacaoLogin = new InformacaoLogin();
				this.usuarioLogado.setInformacaoLogin(informacaoLogin);
				informacaoLogin.setDtLogin(new Date());
				informacaoLogin.setUsuario(usuarioValidado);
				informacaoLogin.setCodSessao(session.getId());
				
				informacaoLoginService.save(informacaoLogin);
				
				return SUCCESS;
			}
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		}
		
		addActionError("Usuário ou senha inválido.");
		return ERROR;
	}

	@Action(value = "fazerLogout", results = {@Result(name = "success", location="/index.jsp"),
            								 @Result(name = "error", location="/index.jsp")})
	public String fazerLogout() {
		TIPO_USUARIO = null;
		
		HttpSession session = request.getSession();
		session.invalidate();

		return SUCCESS;
	}
	
//	@Action(value = "atualizarSenhaAtacadista", results = {@Result(name = "success", location="/atacadista/principalAtacadista.jsp")})
//	public String atualizarSenhaAtacadista() {
//		try {
//			usuarioLogado.getUsuario().setSenha(EncryptionUtils.MD5(novaSenha.trim()));
//			usuarioLogado.getUsuario().setStatus(Usuario.STATUS_ATIVO);
//			
//			usuarioService.save(usuarioLogado.getUsuario());
//
//		} catch (SiscordGenericException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return SUCCESS;
//	}
	
	@Action(value = "atualizarSenhaAdministrador", results = {@Result(name = "success", location="/administrador/principalAdministrador.jsp")})
	public String atualizarSenhaAdministrador() {
		try {
			usuarioLogado.getUsuario().setSenha(EncryptionUtils.MD5(novaSenha.trim()));
			usuarioLogado.getUsuario().setStatus(Usuario.STATUS_ATIVO);
			
			usuarioService.save(usuarioLogado.getUsuario());

		} catch (SiscordGenericException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	// primeiro acesso varejista
	@Action(value = "atualizarSenha", results = {@Result(type="redirectAction", params={"actionName","../buscaProdutos"})})
	public String atualizarSenha() {
		try {
				
			usuarioLogado.getUsuario().setSenha(EncryptionUtils.MD5(novaSenha.trim()));
			usuarioLogado.getUsuario().setStatus(Usuario.STATUS_ATIVO);
			
			usuarioService.save(usuarioLogado.getUsuario());

		} catch (SiscordGenericException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public static Character getTIPO_USUARIO() {
		return TIPO_USUARIO;
	}

	public static void setTIPO_USUARIO(Character tIPO_USUARIO) {
		TIPO_USUARIO = tIPO_USUARIO;
	}

}
