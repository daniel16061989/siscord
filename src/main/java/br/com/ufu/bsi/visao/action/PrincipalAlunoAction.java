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
import br.com.ufu.bsi.session.UsuarioLogado;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value="/principalAluno")
public class PrincipalAlunoAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	private List<Mensagem> mensagensGeraisAluno;
	
	private List<Mensagem> mensagensEspecificasAluno;
	
	public PrincipalAlunoAction() {
		
	}
	
	@Action(value = "index", results = {@Result(name = "success", location = "/aluno/principal.jsp")})
	public String index() {
		mensagensGeraisAluno = new ArrayList<Mensagem>();
		mensagensEspecificasAluno = new ArrayList<Mensagem>();
		try {
			mensagensGeraisAluno = mensagemService.findByStatus(Mensagem.STATUS_ALUNO);
			mensagensEspecificasAluno = mensagemService.findByUsuario(usuarioLogado.getUsuario());
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public List<Mensagem> getMensagensGeraisAluno() {
		return mensagensGeraisAluno;
	}

	public void setMensagensGeraisAluno(List<Mensagem> mensagensGeraisAluno) {
		this.mensagensGeraisAluno = mensagensGeraisAluno;
	}

	public List<Mensagem> getMensagensEspecificasAluno() {
		return mensagensEspecificasAluno;
	}

	public void setMensagensEspecificasAluno(
			List<Mensagem> mensagensEspecificasAluno) {
		this.mensagensEspecificasAluno = mensagensEspecificasAluno;
	}

}