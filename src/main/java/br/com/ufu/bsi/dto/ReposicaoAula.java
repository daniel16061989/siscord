package br.com.ufu.bsi.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reposicoes_aula")
public class ReposicaoAula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_reposicao_aula")
	private Integer idReposicaoAula;
	
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@Column(name = "data_aula")
	private Date dataAula;
	
	@Column(name = "data_aula_reposicao")
	private Date dataAulaReposicao;
	
	@Column(name = "sala_aula_reposicao")
	private String salaAulaReposicao;
	
	@Column(name = "justificativa")
	private String justificativa;

	public Integer getIdReposicaoAula() {
		return idReposicaoAula;
	}

	public void setIdReposicaoAula(Integer idReposicaoAula) {
		this.idReposicaoAula = idReposicaoAula;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getDataAula() {
		return dataAula;
	}

	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}

	public Date getDataAulaReposicao() {
		return dataAulaReposicao;
	}

	public void setDataAulaReposicao(Date dataAulaReposicao) {
		this.dataAulaReposicao = dataAulaReposicao;
	}

	public String getSalaAulaReposicao() {
		return salaAulaReposicao;
	}

	public void setSalaAulaReposicao(String salaAulaReposicao) {
		this.salaAulaReposicao = salaAulaReposicao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

}
