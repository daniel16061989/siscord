package br.com.ufu.bsi.visao.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value="/nav")
public class NavAction extends GenericAction {

	private static final long serialVersionUID = 3398163571561082952L;
	
	@Action(value = "redirectPrincipal", results = @Result(name = "success", location = "/pages/principal.jsp"))
	public String redirectPrincipal(){
		return SUCCESS;
	}

}