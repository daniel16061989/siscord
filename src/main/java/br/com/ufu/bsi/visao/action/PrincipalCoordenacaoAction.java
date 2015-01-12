package br.com.ufu.bsi.visao.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/principalCoordenacao")
public class PrincipalCoordenacaoAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	public PrincipalCoordenacaoAction() {
		
	}
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/principal.jsp")})
	public String index() {
		return SUCCESS;
	}

}
