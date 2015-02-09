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
@Table(name = "disciplinas")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_disciplina")
	private Integer idDisciplina;
	
	@Column(name = "nome_disciplina")
	private String nomeDisciplina;
	
	@Column(name = "codigo_disciplina")
	private String codigoDisciplina;
	
	@Column(name = "descricao_disciplina")
	private String descricaoDisciplina;
	
	@Column(name = "periodo")
	private Integer periodo;
	
	@Column(name = "carga_horaria")
	private Integer cargaHoraria;
	
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;
	
	@Column(name = "horarios_aula")
	private String horariosAula;
	
	@Column(name = "ementa")
	private String ementa;
	
	@Column(name = "bibliografia")
	private String bibliografia;
	
	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getDescricaoDisciplina() {
		return descricaoDisciplina;
	}

	public void setDescricaoDisciplina(String descricaoDisciplina) {
		this.descricaoDisciplina = descricaoDisciplina;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getHorariosAula() {
		return horariosAula;
	}

	public void setHorariosAula(String horariosAula) {
		this.horariosAula = horariosAula;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}

}