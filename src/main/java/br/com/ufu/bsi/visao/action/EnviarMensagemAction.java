package br.com.ufu.bsi.visao.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Mensagem;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/enviarMensagem")
public class EnviarMensagemAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/enviarMensagem.jsp")})
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "enviarMensagemAluno", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
					 								  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String enviarMensagemAluno() {
		String mensagem = request.getParameter("mensagem");
		
		Mensagem m = new Mensagem();
		
		m.setAssunto("Mensagem Geral");
		m.setDataMensagem(new Date());
		m.setLida(false);
		m.setMensagem(mensagem);
		m.setStatus(Mensagem.STATUS_ALUNO);
		
		try {
			mensagemService.save(m);
			index();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}
	
	@Action(value = "enviarMensagemProfessor", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			  										  	  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String enviarMensagemProfessor() {
		String mensagem = request.getParameter("mensagem");
		
		Mensagem m = new Mensagem();
		
		m.setAssunto("Mensagem Geral");
		m.setDataMensagem(new Date());
		m.setLida(false);
		m.setMensagem(mensagem);
		m.setStatus(Mensagem.STATUS_PROFESSOR);
		
		try {
			mensagemService.save(m);
			index();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", "success");
		return SUCCESS;
	}

}