<%@page import="br.com.ufu.bsi.visao.action.LoginAction"%>

<% 
	if(LoginAction.TIPO_USUARIO == null) {
		response.sendRedirect(request.getContextPath() + "/login");
		
	// Aluno
	} else if(LoginAction.TIPO_USUARIO == 'A') {
		response.sendRedirect(request.getContextPath() + "/principalAluno");
		
	// Professor
	} else if(LoginAction.TIPO_USUARIO == 'P') {
		response.sendRedirect(request.getContextPath() + "/principalProfessor");

	// Coordenacao
	} else if(LoginAction.TIPO_USUARIO == 'C') {
		response.sendRedirect(request.getContextPath() + "/principalCoordenacao");
	}
 %>

