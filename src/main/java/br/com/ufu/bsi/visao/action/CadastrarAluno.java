package br.com.ufu.bsi.visao.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Usuario;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value="/cadastrarAluno")
public class CadastrarAluno extends GenericAction {

	private static final long serialVersionUID = 1L;

	private Aluno aluno;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/cadastrarAluno.jsp")})
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "salvarNovoAluno", results = {@Result(name = "success", location = "/login.jsp")})
	public String salvarNovoAluno() {
		try {
			aluno.setSimulacaoAjuste(Boolean.FALSE);
			aluno.getUsuario().setDtCadastro(new Date());
			aluno.getUsuario().setLogin(aluno.getMatricula());
			aluno.getUsuario().setStatus(Usuario.STATUS_ATIVO);
			aluno.getUsuario().setTipoUsuario(Usuario.TIPO_USUARIO_ALUNO);

			alunoService.save(aluno);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}