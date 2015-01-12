package br.com.ufu.bsi.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Objeto que armazena os dados da grade horaria de cada aluno, esta tabela deve ser reiniciada a cada semestre
 * @author daniel
 */
@Entity
@Table(name = "grades_horarias")
public class GradeHoraria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_grade_horaria")
	private Integer idGradeHoraria;
	
	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	public Integer getIdGradeHoraria() {
		return idGradeHoraria;
	}

	public void setIdGradeHoraria(Integer idGradeHoraria) {
		this.idGradeHoraria = idGradeHoraria;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
