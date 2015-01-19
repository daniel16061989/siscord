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
@Table(name = "planos_disciplinas")
public class PlanoDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_plano_disciplina")
	private Integer idPlanoDisciplina;
	
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;
	
	@Column(name = "ementa")
	private String ementa;
	
	@Column(name = "metodologia")
	private String metodologia;
	
	@Column(name = "avaliacao")
	private String avaliacao;
	
	@Column(name = "atendimento")
	private String atendimento;
	
	@Column(name = "recuperacao")
	private String recuperacao;
	
	@Column(name = "bibliografia")
	private String bibliografia;
	
	@Column(name = "status")
	private Character status;

	@Transient
	public static final Character STATUS_ABERTO = 'A';
	@Transient
	public static final Character STATUS_COORDENADOR = 'C';
	@Transient
	public static final Character STATUS_VOLTAR_COORDENADOR = 'D';
	@Transient
	public static final Character STATUS_COLEGIADO = 'B';
	@Transient
	public static final Character STATUS_VOLTAR_COLEGIADO = 'E';
	@Transient
	public static final Character STATUS_FINAL = 'F';
	
	public Integer getIdPlanoDisciplina() {
		return idPlanoDisciplina;
	}

	public void setIdPlanoDisciplina(Integer idPlanoDisciplina) {
		this.idPlanoDisciplina = idPlanoDisciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(String atendimento) {
		this.atendimento = atendimento;
	}

	public String getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(String recuperacao) {
		this.recuperacao = recuperacao;
	}

	public String getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}
