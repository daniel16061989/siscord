package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Mensagem;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProgramaDisciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;
import br.com.ufu.bsi.dto.Usuario;
import br.com.ufu.bsi.util.TratamentoObjeto;

import com.google.gson.Gson;

@ParentPackage("default")
@InterceptorRef("professor")
@Namespace(value="/analisePlanoDisciplina")
public class AnalisePlanoDisciplinaAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<ProgramaPlanoDisciplina> programaPlanoDisciplinas;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/professor/analisePlanoDisciplina.jsp")})
	public String index() {
		Professor p = new Professor();
		p = (Professor) usuarioLogado.getObject();
		
		programaPlanoDisciplinas = new ArrayList<ProgramaPlanoDisciplina>();
		try {
			if(p.getTipoProfessor().equals(Professor.TIPO_PROFESSOR_COORDENADOR)) {
				programaPlanoDisciplinas = programaPlanoDisciplinaService.findByPlanoDisciplinaStatus(PlanoDisciplina.STATUS_COORDENADOR);
			} else if(p.getTipoProfessor().equals(Professor.TIPO_PROFESSOR_COLEGIADO)) {
				programaPlanoDisciplinas = programaPlanoDisciplinaService.findByPlanoDisciplinaStatus(PlanoDisciplina.STATUS_COLEGIADO);
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@Action(value = "buscarPlanoDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			   											@Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarPlanoDisciplina() {
		String idProgramaPlanoDisciplina = request.getParameter("idProgramaPlanoDisciplina");
		Gson gson = new Gson();
	
		ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
		List<ProgramaDisciplina> pds = new ArrayList<ProgramaDisciplina>();
		try { 
			ppd = programaPlanoDisciplinaService.findOne(Integer.parseInt(idProgramaPlanoDisciplina));
			pds = programaDisciplinaService.findByPlanoDisciplina(ppd.getPlanoDisciplina());
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		TratamentoObjeto objeto = new TratamentoObjeto(ppd, pds);
		objeto.setObjectA(ppd);
		objeto.setObjectB(pds);
	
		jsonData.put("success", gson.toJson(objeto));
		return SUCCESS;
	}
	
	@Action(value = "aprovarPlanoDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
														 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String aprovarPlanoDisciplina() {
		String idProgramaPlanoDisciplina = request.getParameter("idProgramaPlanoDisciplina");
		String justificativa = request.getParameter("justificativa");
		
		try {
			ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
			ppd = programaPlanoDisciplinaService.findOne(Integer.parseInt(idProgramaPlanoDisciplina));
			
			PlanoDisciplina p = new PlanoDisciplina();
			p = ppd.getPlanoDisciplina();
			
			enviarMensagemJustificativa(justificativa, p.getProfessor().getUsuario());
		
			if(p.getStatus().equals(PlanoDisciplina.STATUS_COLEGIADO)) {
				p.setStatus(PlanoDisciplina.STATUS_COORDENADOR);
				planoDisciplinaService.save(p);
				
			} else if(p.getStatus().equals(PlanoDisciplina.STATUS_COORDENADOR)) {
				p.setStatus(PlanoDisciplina.STATUS_FINAL);
				planoDisciplinaService.save(p);
			}
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}
	
	@Action(value = "reprovarPlanoDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			 											  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String reprovarPlanoDisciplina() {
		String idProgramaPlanoDisciplina = request.getParameter("idProgramaPlanoDisciplina");

		try {
			ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
			ppd = programaPlanoDisciplinaService.findOne(Integer.parseInt(idProgramaPlanoDisciplina));
			
			PlanoDisciplina p = new PlanoDisciplina();
			p = ppd.getPlanoDisciplina();
			
			if(p.getStatus().equals(PlanoDisciplina.STATUS_COLEGIADO)) {
				p.setStatus(PlanoDisciplina.STATUS_VOLTAR_COLEGIADO);
				planoDisciplinaService.save(p);
				
			} else if(p.getStatus().equals(PlanoDisciplina.STATUS_COORDENADOR)) {
				p.setStatus(PlanoDisciplina.STATUS_VOLTAR_COORDENADOR);
				planoDisciplinaService.save(p);
				
			}
	
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
	
		jsonData.put("success", "success");
		return SUCCESS;
	}

	private void enviarMensagemJustificativa(String justificativa, Usuario usuario) {
		Mensagem m = new Mensagem();
		m.setAssunto("Aviso Plano Disciplina");
		m.setDataMensagem(new Date());
		m.setLida(Boolean.FALSE);
		m.setMensagem(justificativa);
		m.setStatus(Mensagem.STATUS_PROFESSOR);
		m.setUsuario(usuario);
	}
	
	public List<ProgramaPlanoDisciplina> getProgramaPlanoDisciplinas() {
		return programaPlanoDisciplinas;
	}

	public void setProgramaPlanoDisciplinas(List<ProgramaPlanoDisciplina> programaPlanoDisciplinas) {
		this.programaPlanoDisciplinas = programaPlanoDisciplinas;
	}

}