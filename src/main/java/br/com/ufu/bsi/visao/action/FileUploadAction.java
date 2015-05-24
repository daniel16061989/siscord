package br.com.ufu.bsi.visao.action;

import java.io.File;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

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
		return SUCCESS;
 
	}
 
	public String display() {
		return NONE;
	}
}
