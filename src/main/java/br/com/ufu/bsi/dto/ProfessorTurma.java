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
@Table(name = "professores_turmas")
public class ProfessorTurma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_professor_turma")
	private Integer idProfessorTurma;
	
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	public Integer getIdProfessorTurma() {
		return idProfessorTurma;
	}

	public void setIdProfessorTurma(Integer idProfessorTurma) {
		this.idProfessorTurma = idProfessorTurma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
