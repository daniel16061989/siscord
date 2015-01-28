package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.PlanoDisciplina;
import br.com.ufu.bsi.dto.ProgramaDisciplina;
import br.com.ufu.bsi.dto.ProgramaPlanoDisciplina;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value="/planoDisciplinaAluno")
public class PlanoDisciplinaAlunoAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<Disciplina> disciplinas;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/aluno/planoDisciplina.jsp")})
	public String index() {
		disciplinas = new ArrayList<Disciplina>();
		try {
			disciplinas = disciplinaService.findAll();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	@Action(value = "buscarDadosDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
					 									@Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDadosDisciplina() {
		String idDisciplina = request.getParameter("idDisciplina");
		
		Disciplina disciplina = new Disciplina();
		List<ProgramaPlanoDisciplina> programaPlanoDisciplinas = new ArrayList<ProgramaPlanoDisciplina>();
		
		Gson gson = new Gson();
		String jsonPlanoDisciplina = "";
		try {
			disciplina = disciplinaService.findOne(Integer.valueOf(idDisciplina));
			programaPlanoDisciplinas = programaPlanoDisciplinaService.findByDisciplinaAndPlanoDisciplinaStatus(disciplina, PlanoDisciplina.STATUS_FINAL);
			
			if(programaPlanoDisciplinas.size() == 0) {
				jsonData.put("error", "error");
				return ERROR;
			}
			
			jsonPlanoDisciplina = gson.toJson(programaPlanoDisciplinas);
			 
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		
		jsonData.put("success", jsonPlanoDisciplina);
		return SUCCESS;
	}
	
	
	@Action(value = "buscarDadosDisciplinaAula", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
															@Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDadosDisciplinaAula() {
		String idDisciplina = request.getParameter("idDisciplina");
		
		Disciplina disciplina = new Disciplina();
		List<ProgramaPlanoDisciplina> programaPlanoDisciplinas = new ArrayList<ProgramaPlanoDisciplina>();
		List<ProgramaDisciplina> programaDisciplinas = new ArrayList<ProgramaDisciplina>();
		
		Gson gson = new Gson();
		String jsonProgramaDisciplina = "";
		try {
			disciplina = disciplinaService.findOne(Integer.valueOf(idDisciplina));
			programaPlanoDisciplinas = programaPlanoDisciplinaService.findByDisciplinaAndPlanoDisciplinaStatus(disciplina, PlanoDisciplina.STATUS_FINAL);
			
			programaDisciplinas = programaDisciplinaService.findByPlanoDisciplina(programaPlanoDisciplinas.get(0).getPlanoDisciplina());
			
			jsonProgramaDisciplina = gson.toJson(programaDisciplinas);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}

		jsonData.put("success", jsonProgramaDisciplina);
		return SUCCESS;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
