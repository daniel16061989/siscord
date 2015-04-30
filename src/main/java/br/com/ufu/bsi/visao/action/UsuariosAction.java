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
			jsonData.put("error", "Erro ao buscar dados");
			e.printStackTrace();
		} catch (SiscordGenericException e) {
			jsonData.put("error", "Erro ao buscar dados");
			e.printStackTrace();
		}
		jsonData.put("success", jsonUsuario);
		return SUCCESS;
	}
	
	@Action(value = "buscarProfessor",results = {@Result(name="success", type="json", params = {"root","jsonData"}),
	  			  								 @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		Gson gson = new Gson();
		String jsonProfessor = "";
		try {
			professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
			
			jsonProfessor = gson.toJson(professor);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonProfessor);
		return SUCCESS;
	}
	
	@Action(value = "excluirProfessor", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			  									   @Result(name="error", type="json", params = {"root","jsonData"})})
	public String excluirProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		
		Gson gson = new Gson();
		String jsonProfessor = "";
		
		try {
			professor = new Professor();
			professor = professorService.findOne(Integer.parseInt(idProfessor));
		
			professorService.delete(professor);
		
			List<Professor> ps = new ArrayList<Professor>();
			ps = professorService.findAll();
			jsonProfessor = gson.toJson(ps);
			
		} catch (SiscordGenericException e) {
			jsonData.put("error", "Erro: ao excluir o Professor");
			e.printStackTrace();
		}
		jsonData.put("success", jsonProfessor);
		return SUCCESS;
	}
	
	@Action(value = "salvarProfessor", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
				  								  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String salvarProfessor() {
		String idProfessor = request.getParameter("idProfessor");
		String matriculaProfessor = request.getParameter("matriculaProfessor");
		String nomeUsuarioProfessor = request.getParameter("nomeUsuarioProfessor");
		String loginProfessor = request.getParameter("loginProfessor");
		
		Gson gson = new Gson();
		String jsonProfessor = "";
		
		try {
			Professor professor = new Professor();
			professor.setUsuario(new Usuario());
			
			if(idProfessor != null && !"".equals(idProfessor.trim())) {
				professor = professorService.findOne(Integer.parseInt(idProfessor));
			} else {
				professor.getUsuario().setTipoUsuario('P');
				professor.getUsuario().setDtAtivacao(new Date());
				professor.getUsuario().setDtCadastro(new Date());
				professor.getUsuario().setStatus('A');
				professor.getUsuario().setSenha("123456");
			}
			
			professor.getUsuario().setLogin(loginProfessor);
			
			usuarioService.save(professor.getUsuario());
			
			professor.setCodigo(matriculaProfessor);
			professor.setNomeProfessor(nomeUsuarioProfessor);
			
			professorService.save(professor);
		
			List<Professor> ps = new ArrayList<Professor>();
			ps = professorService.findAll();
			jsonProfessor = gson.toJson(ps);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonProfessor);
		return SUCCESS;
	}
	
	@Action(value = "buscarAluno", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
			   					  			  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String buscarAluno() {
		String idAluno = request.getParameter("idAluno");
		
		Gson gson = new Gson();
		String jsonAluno = "";
		try {
			aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
			
			jsonAluno = gson.toJson(aluno);
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonAluno);
		return SUCCESS;
	}
	
	@Action(value = "excluirAluno", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
				  							   @Result(name="error", type="json", params = {"root","jsonData"})})
	public String excluirAluno() {
		String idAluno = request.getParameter("idAluno");
		
		Gson gson = new Gson();
		String jsonAluno = "";
		
		try {
			aluno = new Aluno();
			aluno = alunoService.findOne(Integer.parseInt(idAluno));
		
			alunoService.delete(aluno);
		
			List<Aluno> as = new ArrayList<Aluno>();
			as = alunoService.findAll();
			jsonAluno = gson.toJson(as);
			
		} catch (SiscordGenericException e) {
			jsonData.put("error", "Erro: ao excluir o Aluno");
			e.printStackTrace();
		}
		jsonData.put("success", jsonAluno);
		return SUCCESS;
	}
	
	@Action(value = "salvarAluno", results = {@Result(name="success", type="json", params = {"root","jsonData"}),
	  			  							  @Result(name="error", type="json", params = {"root","jsonData"})})
	public String salvarAluno() {
		String idAluno = request.getParameter("idAluno");
		String matriculaAluno = request.getParameter("matriculaAluno");
		String nomeUsuarioAluno = request.getParameter("nomeAluno");
		String loginAluno = request.getParameter("loginAluno");
		
		Gson gson = new Gson();
		String jsonAluno = "";
		try {
			aluno = new Aluno();
			aluno.setUsuario(new Usuario());
			
			if(idAluno != null && !"".equals(idAluno.trim())) {
				aluno = alunoService.findOne(Integer.parseInt(idAluno));
			} else {
				aluno.getUsuario().setTipoUsuario('A');
				aluno.getUsuario().setDtAtivacao(new Date());
				aluno.getUsuario().setDtCadastro(new Date());
				aluno.getUsuario().setStatus('A');
				aluno.getUsuario().setSenha("123456");
			}
			
			aluno.getUsuario().setLogin(loginAluno);
			
			usuarioService.save(aluno.getUsuario());
			
			aluno.setMatricula(matriculaAluno);
			aluno.setNomeAluno(nomeUsuarioAluno);
			
			alunoService.save(aluno);
		
			List<Aluno> as = new ArrayList<Aluno>();
			as = alunoService.findAll();
			jsonAluno = gson.toJson(as);
			
		} catch (SiscordGenericException e) {
			e.printStackTrace();
		}
		jsonData.put("success", jsonAluno);
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