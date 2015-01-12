package br.com.ufu.bsi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id_usuario", insertable=false, updatable=false)
	private Integer idUsuario;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Column(name="status", nullable=false)
	private Character status;
	
	@Column(name="tipo_usuario", nullable=false)
	private Character tipoUsuario;
	
	@Column(name="dt_cadastro", nullable=false)
	private Date dtCadastro;
	
	@Column(name="dt_ativacao", nullable=true)
	private Date dtAtivacao;
	
	@Column(name="dt_inativacao", nullable=true)
	private Date dtInativacao;
	
	@Transient
	public static final Character STATUS_ATIVO = 'A';
	@Transient
	public static final Character STATUS_INATIVO = 'I';
	@Transient
	public static final Character STATUS_SUSPENSO = 'S';
	@Transient
	public static final Character STATUS_BLOQUEADO = 'B';
	@Transient
	public static final Character STATUS_PRIMEIRO_ACESSO = 'P';
	@Transient
	public static final Character TIPO_USUARIO_PROFESSOR = 'P';
	@Transient
	public static final Character TIPO_USUARIO_ALUNO = 'A';
	@Transient
	public static final Character TIPO_USUARIO_COORDENACAO = 'C';
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Character getStatus() {
		return status;
	}
	
	public void setStatus(Character status) {
		this.status = status;
	}
	
	public Character getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(Character tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
	public Date getDtAtivacao() {
		return dtAtivacao;
	}
	public void setDtAtivacao(Date dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}
	
	public Date getDtInativacao() {
		return dtInativacao;
	}
	
	public void setDtInativacao(Date dtInativacao) {
		this.dtInativacao = dtInativacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + login
				+ ", senha=" + senha + ", status=" + status + ", tipoUsuario="
				+ tipoUsuario + ", dtCadastro=" + dtCadastro + ", dtInativacao=" + dtInativacao + "]";
	}
	
}
