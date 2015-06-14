package br.com.ufu.bsi.visao.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

@ParentPackage("default")
@InterceptorRef("coordenacao")
@Namespace(value="/fileUpload")
public class FileUploadAction extends GenericAction {

	private static final long serialVersionUID = 1L;

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
 
	@Action(value = "index", results = {@Result(name = "success", location = "/coordenacao/fileupload.jsp")})
	public String index() {
		return SUCCESS;
	}
	
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
 
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
 
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
 
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
 
	public File getFileUpload() {
		return fileUpload;
	}
 
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
 
	public String execute() throws Exception{
		if(fileUpload != null) {
			FileInputStream stream = new FileInputStream(fileUpload);
        	InputStreamReader reader = new InputStreamReader(stream);
        	BufferedReader br = new BufferedReader(reader);
        	String linha = br.readLine();
        	while(linha != null) {
        		if(!linha.trim().equals("")) {
	        		String[] celulas = linha.split(",");
	        		
	        		if(!celulas[0].trim().equals("COD_CURSO")) {
//	        		if(!celulas[0].trim().equals("NOME")) {
	        			
	        			Usuario usuario = new Usuario();
	        			Aluno aluno = new Aluno();
	        			
	        			List<Aluno> alunos = alunoService.findByMatricula(celulas[3]);
	        			if(alunos.size() > 1) {
	        				System.out.println("Erro: Matrácula "+celulas[3]+" está duplicada");
	        			}
	        			
	        			if(alunos.size() == 1) {
	        				aluno.setIdAluno(alunos.get(0).getIdAluno());
	        				usuario.setIdUsuario(aluno.getUsuario().getIdUsuario());
	        			}
		        		
		        		usuario.setDtAtivacao(new Date());
		        		usuario.setDtCadastro(new Date());
		        		usuario.setLogin(celulas[3]);
		        		usuario.setSenha("123456");
		        		usuario.setStatus('A');
		        		usuario.setTipoUsuario('A');
		        		
		        		usuario = usuarioService.save(usuario);
		        		
		        		aluno.setEmail(celulas[16]);
		        		aluno.setMatricula(celulas[3]);
		        		aluno.setNomeAluno(celulas[4]);
		        		aluno.setUsuario(usuario);
		        		
		        		alunoService.save(aluno);
		        		
		        	    linha = br.readLine();
	        		} else {
	        			linha = br.readLine();
	        		}
        		}else {
        			linha = br.readLine();
        		}
        	}
        	br.close();
        	reader.close();
        	stream.close();
		}
		return SUCCESS;
	}
 
	public String display() {
		return NONE;
	}
	
	@SuppressWarnings("unused")
	private void adicionarProfessores(String[] celulas) throws SiscordGenericException {
		Professor professor = new Professor();
		Usuario usuario = new Usuario();
		
		usuario.setDtAtivacao(new Date());
		usuario.setDtCadastro(new Date());
		usuario.setLogin(celulas[3]);
		usuario.setSenha("123456");
		usuario.setStatus('A');
		usuario.setTipoUsuario('P');
		
		usuario = usuarioService.save(usuario);
		
		professor.setCodigo(celulas[3]);
		if(celulas[1] == null) {
			professor.setEmail(celulas[2]);
		}else {
			professor.setEmail(celulas[1]);
		}
		professor.setNomeProfessor(celulas[0]);
		professor.setTipoProfessor('N');
		professor.setUsuario(usuario);
		
		professorService.save(professor);
	}

}