package br.com.ufu.bsi.visao.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import br.com.ufu.bsi.dto.Aluno;
import br.com.ufu.bsi.dto.Usuario;

// http://www.mkyong.com/struts2/struts-2-file-upload-example/

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
		        		Usuario usuario = new Usuario();
		        		
		        		usuario.setDtAtivacao(new Date());
		        		usuario.setDtCadastro(new Date());
		        		usuario.setLogin(celulas[3]);
		        		usuario.setSenha("123456");
		        		usuario.setStatus('A');
		        		usuario.setTipoUsuario('A');
		        		
		        		usuario = usuarioService.save(usuario);
		        		
		        		Aluno aluno = new Aluno();
		        		
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
}
