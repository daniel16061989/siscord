package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Turma;

import com.google.gson.Gson;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/disciplinas")
public class DisciplinasAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<Disciplina> disciplinas;
	
	private List<Turma> turmaDisciplinas;
	
	private List<Professor> professorDisciplinas;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/disciplinas.jsp")})
	public String index() {
		
		disciplinas = new ArrayList<Disciplina>();
		turmaDisciplinas = new ArrayList<Turma>();
		professorDisciplinas = new ArrayList<Professor>();
		
		try {
			disciplinas = disciplinaService.findAll();
			turmaDisciplinas = turmaService.findAll();
			professorDisciplinas = professorService.findAll();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarDisciplina", results = {@Result(name = "success", location = "/coordenacao/disciplinas.jsp")})
	public String salvarDisciplina() {
		String idDisciplina = request.getParameter("idDisciplina");
		String codigoDisciplina = request.getParameter("codigoDisciplina");
		String nomeDisciplina = request.getParameter("nomeDisciplina");
		String cargaHoraria = request.getParameter("cargaHoraria");
		String periodoDisciplina = request.getParameter("periodoDisciplina");
		String idProfessor = request.getParameter("idProfessor");
		String idTurma = request.getParameter("idTurma");
		String horariosAula = request.getParameter("horariosAula");
		String ementa = request.getParameter("ementa");
		String bibliografia = request.getParameter("bibliografia");
		
		try {
			Disciplina d = new Disciplina();
			
			Professor p = new Professor();
			p = professorService.findOne(Integer.parseInt(idProfessor));
			
			Turma t = new Turma();
			t = turmaService.findOne(Integer.parseInt(idTurma));
			
			if(idDisciplina != null && !"".equals(idDisciplina.trim())) {
				d = disciplinaService.findOne(Integer.parseInt(idDisciplina));
			}
			
			d.setCargaHoraria(Integer.parseInt(cargaHoraria));
			d.setCodigoDisciplina(codigoDisciplina);
			d.setHorariosAula(horariosAula);
			d.setNomeDisciplina(nomeDisciplina);
			d.setPeriodo(Integer.parseInt(periodoDisciplina));
			d.setProfessor(p);
			d.setTurma(t);
			d.setEmenta(ementa);
			d.setBibliografia(bibliografia);
			
			disciplinaService.save(d);
		
			index();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "buscarDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
					 							   @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDisciplina() {
		String idDisciplina = request.getParameter("idDisciplina");
		Gson gson = new Gson();
		String jsonDisciplina = "";
		
		try {
			Disciplina d = new Disciplina();
			d = disciplinaService.findOne(Integer.parseInt(idDisciplina));
			
			jsonDisciplina = gson.toJson(d);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", jsonDisciplina);
		return SUCCESS;
	}
	
	@Action(value = "excluirDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
													@Result(name="error", type="json", params = {"root","jsonData"})})
	public String excluirDisciplina() {
		String idDisciplina = request.getParameter("idDisciplina");
		
		try {
			Disciplina d = new Disciplina();
			d = disciplinaService.findOne(Integer.parseInt(idDisciplina));
		
			disciplinaService.delete(d);
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", "success");
		return SUCCESS;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Turma> getTurmaDisciplinas() {
		return turmaDisciplinas;
	}

	public void setTurmaDisciplinas(List<Turma> turmaDisciplinas) {
		this.turmaDisciplinas = turmaDisciplinas;
	}

	public List<Professor> getProfessorDisciplinas() {
		return professorDisciplinas;
	}

	public void setProfessorDisciplinas(List<Professor> professorDisciplinas) {
		this.professorDisciplinas = professorDisciplinas;
	}

}