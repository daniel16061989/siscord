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
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.GradeHoraria;
import br.com.ufu.bsi.dto.Mensagem;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/visualizarSimulacaoAjusteMatricula")
public class VisualizarSimulacaoAjusteMatriculaAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private List<GradeHoraria> gradesHorarias;
	
	private List<Aluno> alunosGradeHoraria;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/visualizarSimulacaoAjusteMatricula.jsp")})
	public String index() {
		gradesHorarias = new ArrayList<GradeHoraria>();
		alunosGradeHoraria = new ArrayList<Aluno>();
		try {
//			gradesHorarias = gradeHorariaService.findAll();
			alunosGradeHoraria = alunoService.findBySimulacaoAjuste(Boolean.TRUE);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "buscarGradeHoraria", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			   										 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarGradeHoraria() {
		String idAluno = request.getParameter("idAluno");
		String jsonGradeHoraria = "[";
		
		List<GradeHoraria> gds = new ArrayList<GradeHoraria>();
		Aluno a = new Aluno();
		
		try {
			a = alunoService.findOne(Integer.valueOf(idAluno));
			gds = gradeHorariaService.findByAluno(a);
			
			for(GradeHoraria gh : gds) {
				jsonGradeHoraria = jsonGradeHoraria + "{\"horario\":\""+gh.getDisciplina().getHorariosAula()+
						"\",\"disciplina\":\""+gh.getDisciplina().getCodigoDisciplina() +" "+gh.getDisciplina().getTurma().getCodigoTurma() +"\"},";
			}
			
			if(jsonGradeHoraria.endsWith(",")) {
				jsonGradeHoraria = jsonGradeHoraria.substring(0, jsonGradeHoraria.length() - 1);
			}
			
			jsonGradeHoraria = jsonGradeHoraria + "]";
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", jsonGradeHoraria);
		return SUCCESS;
	}

	@Action(value = "enviarResposta", results = {@Result(name = "success", location = "/coordenacao/visualizarSimulacaoAjusteMatricula.jsp")})
	public String enviarResposta() {
		String tipo = request.getParameter("tipo");
		String idAluno = request.getParameter("aluno");
		String motivo = request.getParameter("motivo");
		
		Aluno aluno = new Aluno();
		Mensagem mensagem = new Mensagem();
		
		try {
			aluno = alunoService.findOne(Integer.valueOf(idAluno));
		
			mensagem.setAssunto("Grade Horaria");
			mensagem.setDataMensagem(new Date());
			mensagem.setLida(false);
			mensagem.setStatus(Mensagem.STATUS_GRADE_HORARIA_ALUNO);
			mensagem.setUsuario(aluno.getUsuario());
			
			// reprovado
			if(tipo.equals("0".toString())) {
				mensagem.setMensagem("Sua Grade Horaria foi ReProvada. " + motivo);
			} else {
				mensagem.setMensagem("Sua Grade Horaria foi Aprovada com sucesso. " + motivo);
			}
			aluno.setSimulacaoAjuste(Boolean.FALSE);
			alunoService.save(aluno);
			mensagemService.save(mensagem);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		index();
		return SUCCESS;
	}
	
	public List<GradeHoraria> getGradesHorarias() {
		return gradesHorarias;
	}

	public void setGradesHorarias(List<GradeHoraria> gradesHorarias) {
		this.gradesHorarias = gradesHorarias;
	}

	public List<Aluno> getAlunosGradeHoraria() {
		return alunosGradeHoraria;
	}

	public void setAlunosGradeHoraria(List<Aluno> alunosGradeHoraria) {
		this.alunosGradeHoraria = alunosGradeHoraria;
	}

}
