package br.com.ufu.bsi.visao.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Usuario;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value = "/aterarSenhaAluno")
public class AlterarSenhaAlunoAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	@Action(value = "index", results = { @Result(name = "success", location = "/aluno/trocarSenha.jsp") })
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "salvarNovaSenha", results = {@Result(name = "success", type = "json", params = { "root", "jsonData" }),
												  @Result(name = "error", type = "json", params = { "root", "jsonData" }) })
	public String buscarPlanoDisciplina() {
		String senhaAtual = request.getParameter("senhaAtual");
		String novaSenha = request.getParameter("novaSenha");
		
		try {
			if(usuarioLogado.getUsuario().getSenha().trim().equals(senhaAtual.trim())) {
			Usuario u = usuarioLogado.getUsuario();
			u.setSenha(novaSenha);
			
			usuarioService.save(u);
			} else {
				jsonData.put("error", "error");
				return ERROR;
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
	
		jsonData.put("success", "success");
		return SUCCESS;
	}
	
}
