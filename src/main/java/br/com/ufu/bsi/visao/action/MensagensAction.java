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
@InterceptorRef("aluno")
@Namespace(value="/mensagens")
public class MensagensAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<Mensagem> mensagens;
	
	public MensagensAction() {
		mensagens = new ArrayList<Mensagem>();
	}
	
	@Action(value = "index", results = {@Result(name = "success", location = "/pages/mensagens.jsp")})
	public String buscaMensagens() {
		return SUCCESS;
	}
	
	@Action(value = "removerMensagem", results = {@Result(name = "success", location = "/pages/mensagens.jsp")})
	public String removerMensagem() {
		try {
			String idMensagem = request.getParameter("id");
			
			Mensagem mensagem = mensagemService.findOne(Integer.valueOf(idMensagem));
			
			mensagem.setStatus("I");
			mensagem.setLida(Boolean.TRUE);
			
			mensagemService.save(mensagem);
			
			buscaMensagens();
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "mensagemLida", results = {@Result(name = "success", location = "/pages/mensagens.jsp")})
	public String mensagemLida() {
		try {
			String idMensagem = request.getParameter("id");
			
			if(idMensagem != null) {
			
				Mensagem mensagem = mensagemService.findOne(Integer.valueOf(idMensagem));
				
				mensagem.setLida(Boolean.TRUE);
				
				mensagemService.save(mensagem);
				
				
				buscaMensagens();
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}
