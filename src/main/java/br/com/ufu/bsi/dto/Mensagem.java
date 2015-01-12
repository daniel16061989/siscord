package br.com.ufu.bsi.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mensagens")
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_mensagem")
	private Integer idMensagem;
	
	@Column(name = "assunto")
	private String assunto;
	
	@Column(name = "mensagem")
	private String mensagem;
	
	@Column(name = "lida")
	private Boolean lida;
	
	@Column(name = "data_mensagem")
	private Date dataMensagem;
	
	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Transient
	private String dataFormatada;
	
	@Transient
	public static String STATUS_GRADE_HORARIA_ALUNO = "G";
	@Transient
	public static String STATUS_ALUNO = "A";
	@Transient
	public static String STATUS_PROFESSOR = "P";
	
	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getLida() {
		return lida;
	}

	public void setLida(Boolean lida) {
		this.lida = lida;
	}

	public Date getDataMensagem() {
		return dataMensagem;
	}

	public void setDataMensagem(Date dataMensagem) {
		this.dataMensagem = dataMensagem;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataFormatada() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String novoFormato = formatador.format(dataMensagem);  
		
		return novoFormato;
	}

}
