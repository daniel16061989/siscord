package br.com.ufu.bsi.visao.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dao.excecoes.SiscordGenericException;
import br.com.ufu.bsi.dto.Disciplina;
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.ReposicaoAula;

@ParentPackage("default")
@InterceptorRef("professor")
@Namespace(value="/reposicaoAula")
public class ReposicaoAulaAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private String dataAula;
	
	private String dataAulaReposicao;
	
	private String sala;
	
	private String justificativa;
	
	private String codDisciplina;
	
	private List<Disciplina> disciplinasProfessor;
	
	public ReposicaoAulaAction() {
		disciplinasProfessor = new ArrayList<Disciplina>();
	}
	
	@Action(value = "index", results = {@Result(name = "success", location = "/professor/reposicaoAula.jsp")})
	public String index() {
		Professor professor = new Professor();
		
		try {
			professor = professorService.findByUsuario(usuarioLogado.getUsuario());
			if(professor != null) {
				disciplinasProfessor = new ArrayList<Disciplina>();
				disciplinasProfessor = disciplinaService.findByProfessor(professor);
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarNovaReposicao", results = {@Result(name = "success", location = "/professor/reposicaoAula.jsp")})
	public String salvarNovaReposicao() {
		try {
			Professor professor = new Professor();
			Disciplina disciplina = new Disciplina();
			ReposicaoAula reposicaoAula = new ReposicaoAula();
			
			professor = professorService.findByUsuario(usuarioLogado.getUsuario());
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			Date dateAula = (Date) formatter.parse(dataAula);
			Date dateAulaReposicao = (Date) formatter.parse(dataAulaReposicao);
			
			disciplina = disciplinaService.findOne(Integer.valueOf(codDisciplina));
			
			reposicaoAula.setDataAula(dateAula);
			reposicaoAula.setDataAulaReposicao(dateAulaReposicao);
			reposicaoAula.setDisciplina(disciplina);
			reposicaoAula.setJustificativa(justificativa);
			reposicaoAula.setProfessor(professor);
			reposicaoAula.setSalaAulaReposicao(sala);
			
			reposicaoAulaService.save(reposicaoAula);
			
			addActionMessage("Reposição de Aula salva com sucesso.");
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getDataAula() {
		return dataAula;
	}

	public void setDataAula(String dataAula) {
		this.dataAula = dataAula;
	}

	public String getDataAulaReposicao() {
		return dataAulaReposicao;
	}

	public void setDataAulaReposicao(String dataAulaReposicao) {
		this.dataAulaReposicao = dataAulaReposicao;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public List<Disciplina> getDisciplinasProfessor() {
		return disciplinasProfessor;
	}

	public void setDisciplinasProfessor(List<Disciplina> disciplinasProfessor) {
		this.disciplinasProfessor = disciplinasProfessor;
	}

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

}