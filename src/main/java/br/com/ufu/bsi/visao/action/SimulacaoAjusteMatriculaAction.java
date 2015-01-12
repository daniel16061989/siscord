package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.GradeHoraria;
import br.com.ufu.bsi.dto.Turma;

@ParentPackage("default")
@InterceptorRef("aluno")
@Namespace(value="/simulacaoAjusteMatricula")
public class SimulacaoAjusteMatriculaAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	@Action(value = "index", results = {@Result(name = "success", location = "/aluno/simulacaoAjusteMatricula.jsp"),
										@Result(name = "aviso", location = "/aluno/avisoSimulacaoAjusteMatricula.jsp")})
	public String index() {
		Aluno a = new Aluno();
		try {
			a = alunoService.findByUsuario(usuarioLogado.getUsuario());
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		if(a.getSimulacaoAjuste()) {
			return "aviso";
		} else {
			return SUCCESS;
		}
	}

	@Action(value = "buscarDisciplinasPeriodo", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			   									   		   @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDisciplinasPeriodo() {
		String periodo = request.getParameter("periodo");
		String json_str = "";
		
		try {
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			disciplinas = disciplinaService.findByPeriodo(Integer.valueOf(periodo));

			json_str = "[";
			for(Disciplina d : disciplinas) {
				json_str = json_str + "{\"codigo\":\""+d.getCodigoDisciplina()+"\",\"nome\":\""+d.getNomeDisciplina()+"\",\"id\":\""+d.getIdDisciplina()+"\"},";
			}
			if(json_str.endsWith(",")) {
				json_str = json_str.substring(0, json_str.length() - 1);
			}
			
			json_str = json_str + "]";
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", json_str);
		return SUCCESS;
	}
	
	@Action(value = "adicionarDisciplina", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
	   		   										  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String adicionarDisciplina() {
		String idDisciplina = request.getParameter("idTurma");
		
		Disciplina disciplina = new Disciplina();
		try {
			disciplina = disciplinaService.findOne(Integer.valueOf(idDisciplina));
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		
		jsonData.put("success", disciplina.getCodigoDisciplina() +" "+disciplina.getTurma().getCodigoTurma()+" -"+disciplina.getHorariosAula());
		return SUCCESS;
	}
	
	@Action(value = "enviarGrade", results = {@Result(name = "success", location="/aluno/avisoSimulacaoAjusteMatricula.jsp")})
	public String enviarGrade() {
		String cargaHoraria = request.getParameter("cargaHoraria");
		
		List<Disciplina> cs = new ArrayList<Disciplina>();
		
		String[] ch = cargaHoraria.split(",");
		
		try {
			Disciplina disciplina = new Disciplina();
			Turma turma = new Turma();
			GradeHoraria g = new GradeHoraria();
			Aluno a = new Aluno();
			
			for(int i = 0; i < ch.length; i++) {
				String codigoTurma = ch[i].substring(7, 10).trim();
				String codigoDisciplina = ch[i].substring(0, 7).trim();
				
				turma = turmaService.findByCodigoTurma(codigoTurma);
				disciplina = disciplinaService.findByCodigoDisciplinaAndTurma(codigoDisciplina, turma);
				a = alunoService.findByUsuario(usuarioLogado.getUsuario());
				
				int aux = 0;
				for(Disciplina d : cs) {
					if(d.getIdDisciplina().equals(disciplina.getIdDisciplina())) {
						aux = 1;
						break;
					}
				}
				if(aux == 0) {
					cs.add(disciplina);
					
					g.setAluno(a);
					g.setDisciplina(disciplina);
					
					gradeHorariaService.save(g);
				}
			}
			a.setSimulacaoAjuste(Boolean.TRUE);
			alunoService.save(a);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
