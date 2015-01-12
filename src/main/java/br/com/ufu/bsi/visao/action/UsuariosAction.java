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
import br.com.ufu.bsi.dto.Professor;
import br.com.ufu.bsi.dto.Usuario;

import com.google.gson.Gson;

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/usuarios")
public class UsuariosAction extends GenericAction {

	private static final long serialVersionUID = 1L;
	
	private Professor professor;
	
	private Aluno aluno;
	
	private List<Usuario> usuarios;
	
	private List<Professor> professorUsuarios;
	
	private List<Aluno> alunoUsuarios;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String index() {
		return SUCCESS;
	}
	
	@Action(value = "buscarDadosTipoUsuario", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			   											 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarDadosTipoUsuario() {
		String tipoUsuario = request.getParameter("tipoUsuario");

		Gson gson = new Gson();
		String jsonUsuario = "";
		try {
			alunoUsuarios = new ArrayList<Aluno>();
			professorUsuarios = new ArrayList<Professor>();
			
			if(tipoUsuario.equals(Usuario.TIPO_USUARIO_ALUNO.toString())) {
				alunoUsuarios = alunoService.findAll();
				jsonUsuario = gson.toJson(alunoUsuarios);
			} else if(tipoUsuario.equals(Usuario.TIPO_USUARIO_PROFESSOR.toString())) {
				professorUsuarios = professorService.findAll();
				jsonUsuario = gson.toJson(professorUsuarios);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonUsuario);
		return SUCCESS;
	}
	
	@Action(value = "buscarProfessor", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String buscarProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		try {
			professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "excluirUsuario", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String excluirProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		try {
			professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
		
			professorService.delete(professor);
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "buscarAluno", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String buscarAluno() {
		String idAluno = request.getParameter("idAluno");
		
		try {
			aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "excluirAluno", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String excluirAluno() {
		String idAluno = request.getParameter("idAluno");
		
		try {
			aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
		
			alunoService.delete(aluno);
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarUsuario", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String salvarUsuario() {
		
		try {
			alunoService.save(aluno);
		
			index();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Professor> getProfessorUsuarios() {
		return professorUsuarios;
	}

	public void setProfessorUsuarios(List<Professor> professorUsuarios) {
		this.professorUsuarios = professorUsuarios;
	}

	public List<Aluno> getAlunoUsuarios() {
		return alunoUsuarios;
	}

	public void setAlunoUsuarios(List<Aluno> alunoUsuarios) {
		this.alunoUsuarios = alunoUsuarios;
	}

}