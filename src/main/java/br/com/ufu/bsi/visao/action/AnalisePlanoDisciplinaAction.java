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

	public List<ProgramaPlanoDisciplina> getProgramaPlanoDisciplinas() {
		return programaPlanoDisciplinas;
	}

	public void setProgramaPlanoDisciplinas(List<ProgramaPlanoDisciplina> programaPlanoDisciplinas) {
		this.programaPlanoDisciplinas = programaPlanoDisciplinas;
	}

}