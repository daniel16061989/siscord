package br.com.ufu.bsi.listener;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ufu.bsi.session.UsuarioLogado;

import com.opensymphony.xwork2.ActionContext;


public class SiscordFiltro implements Filter {

	private String errorURL;

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        errorURL = filterConfig.getInitParameter("onError");
        if (errorURL == null) {
            errorURL = "/index.jsp";
        }
    }

	@SuppressWarnings("rawtypes")
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map session = ActionContext.getContext().getSession();
        if(session != null) {
        	chain.doFilter(request, response);
        } else {
            request.setAttribute("errorMsg", "Authentication required");
            request.getRequestDispatcher(errorURL).forward(request, response);
        }
        
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession sessao = req.getSession();
        UsuarioLogado usuarioLogado = (UsuarioLogado) sessao.getAttribute("usuarioLogado");
        if(usuarioLogado != null && usuarioLogado.getUsuario() != null)
        	System.out.println(usuarioLogado);
        System.out.println(req.getPathInfo());
        System.out.println(req.getPathTranslated());
        System.out.println(req.getServletContext());
    } 

	@Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
