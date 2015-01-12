package br.com.ufu.bsi.listener;

import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.session.UsuarioLogado;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ProfessorValidadorInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (invocation.getProxy().getActionName().equals("fazerLogin")
				|| invocation.getProxy().getNamespace().equals("/registrar")) {
			return invocation.invoke();
		}

		if (invocation.getProxy().getActionName().equals("fazerLogout")) {
			return invocation.invoke();
		}
		
		UsuarioLogado usuarioLogado = (UsuarioLogado) invocation.getInvocationContext().getSession().get("usuarioLogado");
		if (!(usuarioLogado != null && usuarioLogado.getUsuario() != null)) {
			return "naoLogado";
		}
		if (!usuarioLogado.getUsuario().getTipoUsuario().equals(Usuario.TIPO_USUARIO_PROFESSOR))
			return "inicio";

		return invocation.invoke();
	}

}
