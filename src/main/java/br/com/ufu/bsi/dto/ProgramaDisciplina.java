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
@Table(name = "programas_disciplinas")
public class ProgramaDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_programa_disciplina")
	private Integer idProgramaDisciplina;

	@Column(name = "aula_semestre")
	private Integer aulaSemestre;

	@Column(name = "data_aula")
	private Date dataAula;
	
	@Column(name = "conteudo_aula")
	private String conteudoAula;
	
	@ManyToOne
	@JoinColumn(name = "id_plano_disciplina")
	private PlanoDisciplina planoDisciplina;

	public Integer getIdProgramaDisciplina() {
		return idProgramaDisciplina;
	}

	public void setIdProgramaDisciplina(Integer idProgramaDisciplina) {
		this.idProgramaDisciplina = idProgramaDisciplina;
	}

	public Integer getAulaSemestre() {
		return aulaSemestre;
	}

	public void setAulaSemestre(Integer aulaSemestre) {
		this.aulaSemestre = aulaSemestre;
	}
	
	public Date getDataAula() {
		return dataAula;
	}

	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}

	public String getConteudoAula() {
		return conteudoAula;
	}

	public void setConteudoAula(String conteudoAula) {
		this.conteudoAula = conteudoAula;
	}

	public PlanoDisciplina getPlanoDisciplina() {
		return planoDisciplina;
	}

	public void setPlanoDisciplina(PlanoDisciplina planoDisciplina) {
		this.planoDisciplina = planoDisciplina;
	}

}
