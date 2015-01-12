package br.com.ufu.bsi.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "programas_planos_disciplinas")
public class ProgramaPlanoDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_programa_plano_disciplina")
	private Integer idProgramaPlanoDisciplina;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "id_plano_disciplina")
	private PlanoDisciplina planoDisciplina;
	
	@Column(name = "status")
	private String status;
	
	@Transient
	public static final String STATUS_PROGRAMA_DISCIPLINA_ACEITO = "A";
	@Transient
	public static final String STATUS_PROGRAMA_DISCIPLINA_RECUSADO = "R";
	@Transient
	public static final String STATUS_PROGRAMA_DISCIPLINA_ESPERA = "E";
	
	public Integer getIdProgramaPlanoDisciplina() {
		return idProgramaPlanoDisciplina;
	}

	public void setIdProgramaPlanoDisciplina(Integer idProgramaPlanoDisciplina) {
		this.idProgramaPlanoDisciplina = idProgramaPlanoDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public PlanoDisciplina getPlanoDisciplina() {
		return planoDisciplina;
	}

	public void setPlanoDisciplina(PlanoDisciplina planoDisciplina) {
		this.planoDisciplina = planoDisciplina;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
