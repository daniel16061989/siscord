package br.com.ufu.bsi.session;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import br.com.ufu.bsi.constant.Constantes;
import br.com.ufu.bsi.dto.Usuario;

/**
 * Faz o controle dos usuários logados no Sistema.
 */
@Component("sessionConcierge")
public final class SessionConcierge implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpSession> usuariosLogados;

	public SessionConcierge() {
		this.usuariosLogados = new HashMap<String, HttpSession>();
	}

	public synchronized boolean verificaUsuarioLogado(String nomeUsuario) {
		if (usuariosLogados.containsKey(nomeUsuario)) {
			return true;
		}
		return false;
	}

	public synchronized boolean adicionarUsuarioLogado(String nomeUsuario, HttpSession session) {
		if (verificaUsuarioLogado(nomeUsuario)) {
			return false;
		} else {
			usuariosLogados.put(nomeUsuario, session);
			return true;
		}
	}

	public synchronized void removerUsuarioLogado(String userName) {
		HttpSession session = usuariosLogados.get(userName);
		if (session != null) {
			session.invalidate();
		}
	}

	public synchronized void removeSession(HttpSession session) {
		Usuario usuario = null;
		UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute(Constantes.CHAVE_USUARIO_LOGADO);
		if (usuarioLogado != null) {
			usuario = usuarioLogado.getUsuario();
		}

		if (usuario != null) {
			usuariosLogados.remove(usuario.getLogin());
		}
	}

}