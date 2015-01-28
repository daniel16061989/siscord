package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;

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
//		Gson gson = new Gson();
		String jsonProgramaPlanoDisciplina = "";
	
		try { 
			ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
			ppd = programaPlanoDisciplinaService.findOne(Integer.parseInt(idProgramaPlanoDisciplina));
			
//			jsonProgramaPlanoDisciplina = gson.toJson(ppd);
			jsonProgramaPlanoDisciplina = ppd.getIdProgramaPlanoDisciplina()+"&"+ppd.getDisciplina().getCodigoDisciplina()+"&"+ppd.getPlanoDisciplina().getEmenta()+"&"+
					ppd.getPlanoDisciplina().getMetodologia()+"&"+ppd.getPlanoDisciplina().getAvaliacao()+"&"+ppd.getPlanoDisciplina().getAtendimento()+"&"+
					ppd.getPlanoDisciplina().getRecuperacao()+"&"+ppd.getPlanoDisciplina().getBibliografia();
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
	
		jsonData.put("success", jsonProgramaPlanoDisciplina);
		return SUCCESS;
	}
	
	@Action(value = "aprovarPlanoDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
														 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String aprovarPlanoDisciplina() {
		String idProgramaPlanoDisciplina = request.getParameter("idProgramaPlanoDisciplina");
		
		try {
			ProgramaPlanoDisciplina ppd = new ProgramaPlanoDisciplina();
			ppd = programaPlanoDisciplinaService.findOne(Integer.parseInt(idProgramaPlanoDisciplina));
			
			PlanoDisciplina p = new PlanoDisciplina();
			p = ppd.getPlanoDisciplina();
		
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

	public List<ProgramaPlanoDisciplina> getProgramaPlanoDisciplinas() {
		return programaPlanoDisciplinas;
	}

	public void setProgramaPlanoDisciplinas(List<ProgramaPlanoDisciplina> programaPlanoDisciplinas) {
		this.programaPlanoDisciplinas = programaPlanoDisciplinas;
	}

}