package br.com.ufu.bsi.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_aluno")
	private Integer idAluno;
	
	@Column(name = "nome_aluno")
	private String nomeAluno;
	
	@Column(name = "matricula")
	private String matricula;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	// Se true o aluno enviou uma simulaçao e esta esperando resposta
	@Column(name = "simulacao_ajuste")
	private Boolean simulacaoAjuste;
	
	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getSimulacaoAjuste() {
		return simulacaoAjuste;
	}

	public void setSimulacaoAjuste(Boolean simulacaoAjuste) {
		this.simulacaoAjuste = simulacaoAjuste;
	}
	
}