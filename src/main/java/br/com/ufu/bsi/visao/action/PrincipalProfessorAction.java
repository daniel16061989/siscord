package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Mensagem;

@ParentPackage("default")
@InterceptorRef("professor")
@Namespace(value="/principalProfessor")
public class PrincipalProfessorAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<Mensagem> mensagensGeraisProfessor;
	
	public PrincipalProfessorAction() {
		
	}
	
	@Action(value = "index", results = {@Result(name = "success", location = "/professor/principal.jsp")})
	public String index() {
		mensagensGeraisProfessor = new ArrayList<Mensagem>();
		try {
			mensagensGeraisProfessor = mensagemService.findByStatus(Mensagem.STATUS_PROFESSOR);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public List<Mensagem> getMensagensGeraisProfessor() {
		return mensagensGeraisProfessor;
	}

	public void setMensagensGeraisProfessor(List<Mensagem> mensagensGeraisProfessor) {
		this.mensagensGeraisProfessor = mensagensGeraisProfessor;
	}

}