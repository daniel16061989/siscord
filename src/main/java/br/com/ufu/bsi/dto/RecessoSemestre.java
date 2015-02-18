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
@Table(name = "recesso_semestre")
public class RecessoSemestre implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "id_recesso_semestre")
	private Integer idRecessoSemestre;

	@ManyToOne
	@JoinColumn(name = "id_semestre")
	private Semestre semestre;
	
	@Column(name = "data_recesso")
	private Date dataRecesso;

	public Integer getIdRecessoSemestre() {
		return idRecessoSemestre;
	}

	public void setIdRecessoSemestre(Integer idRecessoSemestre) {
		this.idRecessoSemestre = idRecessoSemestre;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Date getDataRecesso() {
		return dataRecesso;
	}

	public void setDataRecesso(Date dataRecesso) {
		this.dataRecesso = dataRecesso;
	}
	
}