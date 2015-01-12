package br.com.ufu.bsi.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.ufu.bsi.constant.Constantes;
import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.service.InformacaoLoginService;
import br.com.ufu.bsi.session.SessionConcierge;
import br.com.ufu.bsi.session.UsuarioLogado;

public class SessionListener extends RequestContextListener implements HttpSessionListener {

	private static Logger log = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// nothing to be done here
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		SessionConcierge sessionConcierge = (SessionConcierge) ctx.getBean("sessionConcierge");

		UsuarioLogado usuarioLogado = (UsuarioLogado) session.getAttribute(Constantes.CHAVE_USUARIO_LOGADO);
		if (usuarioLogado != null && usuarioLogado.getInformacaoLogin() != null) {
			try {
				// salva informacaoLogin
				InformacaoLoginService informacaoLoginService = (InformacaoLoginService) ctx.getBean("informacaoLoginService");
				usuarioLogado.getInformacaoLogin().setDtLogout(new Date());
				informacaoLoginService.save(usuarioLogado.getInformacaoLogin());
			} catch (SiscordGenericException e) {
				log.error(e.getMessage(), e);
			}
		}
		sessionConcierge.removeSession(session);
	}
}