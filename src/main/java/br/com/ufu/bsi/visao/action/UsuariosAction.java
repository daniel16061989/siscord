package br.com.ufu.bsi.visao.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.constant.Constantes;
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
	
	private Professor novoProfessor;
	
	private Professor salvarProfessor;
	
	private Aluno salvarAluno;
	
	private List<Usuario> usuarios;
	
	private List<Professor> professorUsuarios;
	
	private List<Aluno> alunoUsuarios;
	
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String index() {
		novoProfessor = new Professor();
		
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
	
	@Action(value = "buscarProfessor", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			  									  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		Gson gson = new Gson();
		String jsonUsuario = "";
		
		try {
			Professor professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
			
			jsonUsuario = gson.toJson(professor);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonUsuario);
		return SUCCESS;
	}
	
	@Action(value = "excluirProfessor", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String excluirProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		try {
			Professor professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
		
			professorService.delete(professor);
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarProfessor", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String salvarProfessor() {
		try {
			if(salvarProfessor.getUsuario() != null) {
				Usuario u = new Usuario();
				u = usuarioService.findOne(salvarProfessor.getUsuario().getIdUsuario());
				salvarProfessor.setUsuario(u);
				
				professorService.save(salvarProfessor);
			
				index();
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarNovoProfessor", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String salvarNovoProfessor() {
		try {
				Usuario u = new Usuario();
				u.setDtCadastro(new Date());
				u.setLogin(novoProfessor.getCodigo());
				u.setSenha(Constantes.SENHA_PADRAO_DO_SISTEMA);
				u.setStatus(Usuario.STATUS_ATIVO);
				u.setTipoUsuario(Usuario.TIPO_USUARIO_PROFESSOR);
				
				u = usuarioService.save(u);
				
				novoProfessor.setUsuario(u);
				
				professorService.save(novoProfessor);
			
				addActionMessage("Professor salvo com sucesso.");
				
				index();
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "buscarAluno", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
											  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarAluno() {
		String idAluno = request.getParameter("idAluno");
		
		Gson gson = new Gson();
		String jsonUsuario = "";
		
		try {
			Aluno aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
			jsonUsuario = gson.toJson(aluno);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonUsuario);
		return SUCCESS;
	}
	
	@Action(value = "excluirAluno", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String excluirAluno() {
		String idAluno = request.getParameter("idAluno");
		
		try {
			Aluno aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
		
			alunoService.delete(aluno);
		
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "salvarAluno", results = {@Result(name = "success", location = "/coordenacao/usuarios.jsp")})
	public String salvarAluno() {
		try {
			if(salvarAluno.getUsuario() != null) {
				Usuario u = new Usuario();
				u = usuarioService.findOne(salvarAluno.getUsuario().getIdUsuario());
				salvarAluno.setUsuario(u);
				
				alunoService.save(salvarAluno);
			
				index();
			}
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Professor getSalvarProfessor() {
		return salvarProfessor;
	}

	public void setSalvarProfessor(Professor salvarProfessor) {
		this.salvarProfessor = salvarProfessor;
	}

	public Aluno getSalvarAluno() {
		return salvarAluno;
	}

	public void setSalvarAluno(Aluno salvarAluno) {
		this.salvarAluno = salvarAluno;
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

	public Professor getNovoProfessor() {
		return novoProfessor;
	}

	public void setNovoProfessor(Professor novoProfessor) {
		this.novoProfessor = novoProfessor;
	}

}