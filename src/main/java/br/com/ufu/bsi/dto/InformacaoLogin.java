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

@Entity
@Table(name = "informacoes_login")
public class InformacaoLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_informacao_login")
	private Integer idInformacaoLogin;
	
	@Column(name = "cod_sessao")
	private String codSessao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "dt_login") 
	private Date dtLogin;
	
	@Column(name = "dt_logout")
	private Date dtLogout;

	public Integer getIdInformacaoLogin() {
		return idInformacaoLogin;
	}

	public void setIdInformacaoLogin(Integer idInformacaoLogin) {
		this.idInformacaoLogin = idInformacaoLogin;
	}

	public String getCodSessao() {
		return codSessao;
	}

	public void setCodSessao(String codSessao) {
		this.codSessao = codSessao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDtLogin() {
		return dtLogin;
	}

	public void setDtLogin(Date dtLogin) {
		this.dtLogin = dtLogin;
	}

	public Date getDtLogout() {
		return dtLogout;
	}

	public void setDtLogout(Date dtLogout) {
		this.dtLogout = dtLogout;
	}
	
	public String getDtLoginFormatada() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dtLogin);
	}
	
}
